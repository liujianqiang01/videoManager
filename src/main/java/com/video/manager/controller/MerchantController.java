package com.video.manager.controller;

import com.github.pagehelper.PageInfo;
import com.video.manager.model.BasePage;
import com.video.manager.model.TMerchant;
import com.video.manager.model.TMerchantPrice;
import com.video.manager.model.WebResult;
import com.video.manager.service.MerchantService;
import com.video.manager.utils.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: liujianqiang
 * @Date: 2019-01-17
 * @Description:
 */
@Controller
@RequestMapping("admin/merchant")
public class MerchantController {
    @Autowired
    MerchantService merchantService;
    @RequestMapping(method = RequestMethod.GET)
    public String index(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10")int pageSize,
                        TMerchant merchant, ModelMap map){
        BasePage.beanToMap(merchant,map);
        PageInfo<TMerchant> orderPageInfo =merchantService.getMerchant(merchant,page,pageSize);
        BasePage.page(orderPageInfo,map);
        return "/merchant/index";
    }

//    /**
//     * Excel表格导出接口
//     * @param response response对象
//     * @throws IOException 抛IO异常
//     */
//    @GetMapping("excelDownload")
//    public void excelDownload(HttpServletResponse response) throws IOException {
//        String fileName = "ExcelTest";
//        ExcelUtil.generateExcel(response,fileName);
//    }
    @GetMapping("applyPrice")
    public String applyPrice(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10")int pageSize,
                             TMerchantPrice tMerchantPrice, ModelMap map){
        BasePage.beanToMap(tMerchantPrice,map);
        PageInfo<TMerchantPrice> orderPageInfo =merchantService.getMerchantPrice(tMerchantPrice,page,pageSize);
        BasePage.page(orderPageInfo,map);
        return "/merchantPrice/index";
    }
    @PostMapping("pass")
    @ResponseBody
    public WebResult pass(Integer id,Integer state){
        merchantService.pass(id,state);
        return WebResult.success();
    }

    /**
     * 进入编辑页面
     * @return
     */
    @RequestMapping(value = "edit",method = RequestMethod.GET)
    public String edit(Integer id, ModelMap map){
        map.put("id",id);
        return "/merchant/edit";
    }
    @PostMapping("saveEdit")
    @ResponseBody
    public WebResult saveEdit(TMerchant merchant){
      if(merchant != null && merchant.getId() != null && merchant.getRate() != null) {
          merchantService.update(merchant);
      }
        return WebResult.success();
    }

}