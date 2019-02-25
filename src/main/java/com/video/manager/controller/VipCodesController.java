package com.video.manager.controller;

import com.github.pagehelper.PageInfo;
import com.video.manager.model.*;
import com.video.manager.service.MerchantService;
import com.video.manager.service.VipCodesService;
import com.video.manager.utils.DateUtils;
import com.video.manager.utils.ExcelUtil;
import com.video.manager.utils.StringUtils;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: liujianqiang
 * @Date: 2019-01-17
 * @Description:
 */
@Controller
@RequestMapping("admin/vipCodes")
@Log4j
public class VipCodesController {
    @Autowired
    VipCodesService vipCodesService;
    @Autowired
    MerchantService merchantService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize,
                        TVipCodes vipCodes, ModelMap map) {
        BasePage.beanToMap(vipCodes, map);
        PageInfo<TVipCodes> orderPageInfo = vipCodesService.getVipCodes(vipCodes, page, pageSize);
        BasePage.page(orderPageInfo, map);
        return "/vipCodes/index";
    }

    @PostMapping(value = "upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        String fileName =  file.getOriginalFilename();
        if (file == null) {
            log.info("file不能为空");
            return "文件为空";
        }
        boolean notNull = false;
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            log.info("上传文件格式不正确");
        }
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        try {
            InputStream is = file.getInputStream();
            Workbook wb = null;
            if (isExcel2003) {
                wb = new HSSFWorkbook(is);
            } else {
                wb = new XSSFWorkbook(is);
            }
            //有多少个sheet
            int sheets = wb.getNumberOfSheets();
            for (int i = 0; i < sheets; i++) {
                Sheet sheet = wb.getSheetAt(i);
                //获取多少行
                int rows = sheet.getPhysicalNumberOfRows();

                //遍历每一行，注意：第 0 行为标题
                for (int j = 0; j < rows; j++) {
                    //获得第 j 行
                    Row row = sheet.getRow(j);
                    if(row != null) {
                        Cell cell = row.getCell(0);
                        String code = "";
                        if(cell != null) {
                            if (cell.getCellType() == 0) {
                                code = (int) row.getCell(0).getNumericCellValue() + "";
                            } else if (cell.getCellType() == 1) {
                                code = cell.getStringCellValue();
                            } else if (cell.getCellType() == 2) {
                                code = cell.getCellFormula();
                            }
                            if(!StringUtils.isEmpty(code)){
                                if(sheet.getSheetName().equals("商户号")){
                                    addMerchant(code);
                                }else {
                                    addVipCodes(code,sheet.getSheetName());
                                }
                            }
                            log.info("code=====>" + code);
                        }

                    }
                }
            }

        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return "导入异常";
        }
        return "redirect:/admin/vipCodes";
    }

    /**
     * 导入会员卡
     */
    private void addVipCodes(String vipCode,String sheetName){
        TVipCodes codes = new TVipCodes();
        codes.setVipCode(vipCode);
        codes.setVipState(1);
        if("月卡".equals(sheetName)){
            codes.setVipType(1);
            codes.setIndate(30);
        }else if("季卡".equals(sheetName)){
            codes.setVipType(2);
            codes.setIndate(90);
        }else if("年卡".equals(sheetName)){
            codes.setVipType(3);
            codes.setIndate(365);
        }
        try {
            vipCodesService.addVipCodes(codes);
        }catch (Exception e){
            log.error("导入会员卡失败 vipCode = "+vipCode,e);
        }
    }

    /**
     * 导入商户
     */
    private void addMerchant(String merchantId){
        TMerchant merchant = new TMerchant();
        merchant.setMenchantId(merchantId);
        merchant.setRate(BigDecimal.valueOf(0.5));
        try {
            merchantService.addMerchant(merchant);
        }catch (Exception e){
            log.error("导入商户号失败 merchantId = "+merchantId,e);
        }

    }
    @GetMapping(value = "export")
    @ResponseBody
    public WebResult export(TVipCodes tVipCodes, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(StringUtils.isEmpty(tVipCodes.getMerchantId())){
            return WebResult.needParams("商户号不能为空");
        }
        String fileName = String.valueOf(System.currentTimeMillis());
        List<String[]> listStr = new ArrayList<>();
        String[] strArray;
        tVipCodes.setVipState(0);
        PageInfo<TVipCodes> vipCodes = vipCodesService.getVipCodes(tVipCodes, 0, 100000);
        for(TVipCodes codes : vipCodes.getList()){
            strArray = new String[3];
            strArray[0] = codes.getVipCode();
            if(codes.getExportDate() != null){
                strArray[1] = DateFormatUtils.format(codes.getExportDate(),"yyyy-MM-dd");
            }else {
                strArray[1] = "";
            }
            String typeName = "";
            if(codes.getVipType() == 1){
                typeName = "月卡";
            }else if(codes.getVipType() == 2){
                typeName = "季卡";
            }else {
                typeName = "年卡";
            }
            strArray[2] = typeName;
            listStr.add(strArray);
        }
        String[] titles ={"会员卡"};
        ExcelUtil.generateExcel(response,fileName,titles,listStr);
        return WebResult.success();
    }
}