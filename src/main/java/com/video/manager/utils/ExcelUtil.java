package com.video.manager.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.usermodel.HeaderFooter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: liujianqiang
 * @Date: 2019-01-23
 * @Description:
 */
public class ExcelUtil {

    public static void generateExcel(HttpServletResponse response, String fileName) throws IOException {
        //创建工作薄
        XSSFWorkbook workbook = new XSSFWorkbook();
        //创建表单
        XSSFSheet sheet = genSheet(workbook, "testExcel");
        //创建表单样式
        XSSFCellStyle titleStyle = genTitleStyle(workbook);//创建标题样式
        XSSFCellStyle contextStyle = genContextStyle(workbook);//创建文本样式

        //创建Excel
        genExcel(sheet, titleStyle, contextStyle);


        //准备将Excel的输出流通过response输出到页面下载
        //八进制输出流
        response.setContentType("application/octet-stream");

        //设置导出Excel的名称
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");

        //刷新缓冲
        response.flushBuffer();

        //将工作薄写入文件输出流中
        workbook.write(response.getOutputStream());
        //文本文件输出流，释放资源
    }

    public static void genExcel(XSSFSheet sheet,XSSFCellStyle titleStyle,XSSFCellStyle contextStyle) {

        //根据Excel列名长度，指定列名宽度  Excel总共10列
        for (int i = 0; i < 10; i++) {
            if (i == 0) {
                sheet.setColumnWidth(i, 1500);
            } else if (i == 2 || i == 3 || i == 4) {
                sheet.setColumnWidth(i, 4000);
            } else if (i == 9) {
                sheet.setColumnWidth(i, 3000);
            } else {
                sheet.setColumnWidth(i, 2000);
            }
        }

        //设置标题位置
        sheet.addMergedRegion(new CellRangeAddress(
                0, //first row
                0, //last row
                0, //first column
                9 //last column
        ));

        XSSFRow row = sheet.createRow(0);//创建第一行，为标题，index从0开始
        XSSFCell cell;
        cell = row.createCell(0);//创建一列
        cell.setCellValue("xxx 幼儿园一年级二班学生信息");//标题
        cell.setCellStyle(titleStyle);//设置标题样式

        row = sheet.createRow(1);//创建第二行
        cell = row.createCell(0);//创建第二行第一列
        cell.setCellValue("姓名");//第二行第一列内容
        cell.setCellStyle(contextStyle);//设置样式
        cell = row.createCell(1);//2 row 1 column
        cell.setCellValue("性别");
        cell.setCellStyle(contextStyle);
        cell = row.createCell(2);//2 row 2 column
        cell.setCellValue("学号");
        cell.setCellStyle(contextStyle);
        cell = row.createCell(3);//2 row 3 column
        cell.setCellValue("出生年月");
        cell.setCellStyle(contextStyle);
        cell = row.createCell(4);//2 row 4 column
        cell.setCellValue("家庭住址");
        cell.setCellStyle(contextStyle);
        cell = row.createCell(5);//2 row 5 column
        cell.setCellValue("语文");
        cell.setCellStyle(contextStyle);
        cell = row.createCell(6);//2 row 6 column
        cell.setCellValue("英语");
        cell.setCellStyle(contextStyle);
        cell = row.createCell(7);//2 row 7 column
        cell.setCellValue("数学");
        cell.setCellStyle(contextStyle);
        cell = row.createCell(8);//2 row 8 column
        cell.setCellValue("自然");
        cell.setCellStyle(contextStyle);
        cell = row.createCell(9);//2 row 9 column
        cell.setCellValue("科学");
        cell.setCellStyle(contextStyle);

		/*
			实际填充数据的时候，对可能为空的数据要进行处理，要先进行判断，否则报错
			String value = Test.getVal();
			if(StringUtils.isNotBlank(value)) {
				cell.setCellValue(value);
			} else{
			    cell.setCellValue(" ");
			}
		*/
        //从数据库取数据填充到Excel，这步省略，添加模拟数据
        for(int i = 2 ; i<15;i++){//i从2开始计数，因为上面已经创建了 0 1行
            row=sheet.createRow(i);//创建第三行
            cell=row.createCell(0);//创建第三行第一列
            cell.setCellValue("小明");//第三行第一列的值
            cell.setCellStyle(contextStyle);//设置样式
            cell=row.createCell(1);//2 row 1 column
            cell.setCellValue("男");
            cell.setCellStyle(contextStyle);
            cell=row.createCell(2);//2 row 2 column
            cell.setCellValue("NO20180901"+i);
            cell.setCellStyle(contextStyle);
            cell=row.createCell(3);//2 row 3 column
            cell.setCellValue("2000/02/01");
            cell.setCellStyle(contextStyle);
            cell=row.createCell(4);//2 row 4 column
            cell.setCellValue("成都市天府广场");
            cell.setCellStyle(contextStyle);
            cell=row.createCell(5);//2 row 5 column
            cell.setCellValue("90");
            cell.setCellStyle(contextStyle);
            cell=row.createCell(6);//2 row 6 column
            cell.setCellValue("90");
            cell.setCellStyle(contextStyle);
            cell=row.createCell(7);//2 row 7 column
            cell.setCellValue("90");
            cell.setCellStyle(contextStyle);
            cell=row.createCell(8);//2 row 8 column
            cell.setCellValue("98");
            cell.setCellStyle(contextStyle);
            cell=row.createCell(9);//2 row 9 column
            cell.setCellValue("100");
            cell.setCellStyle(contextStyle);
        }
    }

    //设置表单，并生成表单
    public static XSSFSheet genSheet(XSSFWorkbook workbook, String sheetName){
        //生成表单
        XSSFSheet sheet = workbook.createSheet(sheetName);
        //设置表单文本居中
        sheet.setHorizontallyCenter(true);
        sheet.setFitToPage(false);
        //打印时在底部右边显示文本页信息
        Footer footer = sheet.getFooter();
        footer.setRight( "Page " + HeaderFooter.numPages()+ " Of "+ HeaderFooter.page());
        //打印时在头部右边显示Excel创建日期信息
        Header header = sheet.getHeader();
        header.setRight("Create Date " + HeaderFooter.date() + " " + HeaderFooter.time());
        //设置打印方式
        XSSFPrintSetup ps = sheet.getPrintSetup();
        ps.setLandscape(true); // true：横向打印，false：竖向打印 ，因为列数较多，推荐在打印时横向打印
        ps.setPaperSize(HSSFPrintSetup.A4_PAPERSIZE); //打印尺寸大小设置为A4纸大小
        return sheet;
    }

    //创建文本样式
    public static XSSFCellStyle genContextStyle(XSSFWorkbook workbook){
        XSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);//文本水平居中显示
        style.setVerticalAlignment(VerticalAlignment.CENTER);//文本竖直居中显示
        style.setWrapText(true);//文本自动换行

        //生成Excel表单，需要给文本添加边框样式和颜色
        /*
             CellStyle.BORDER_DOUBLE      双边线
             CellStyle.BORDER_THIN        细边线
             CellStyle.BORDER_MEDIUM      中等边线
             CellStyle.BORDER_DASHED      虚线边线
             CellStyle.BORDER_HAIR        小圆点虚线边线
             CellStyle.BORDER_THICK       粗边线
         */
        style.setBorderBottom(BorderStyle.THIN);//设置文本边框
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);

        return style;
    }

    //生成标题样式
    public static XSSFCellStyle genTitleStyle(XSSFWorkbook workbook){

        XSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setWrapText(true);

        //标题居中，没有边框，所以这里没有设置边框，设置标题文字样式
        XSSFFont titleFont = workbook.createFont();
        titleFont.setBold(true);//加粗
        titleFont.setFontHeight((short)15);//文字尺寸
        titleFont.setFontHeightInPoints((short)15);
        style.setFont(titleFont);

        return style;
    }
}