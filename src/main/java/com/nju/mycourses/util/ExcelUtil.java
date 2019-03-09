package com.nju.mycourses.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Clanner on 2017/5/10.
 */
public class ExcelUtil {

    private ExcelUtil() {
    }

    private final String excel2003L = ".xls";//2003- 版本的excel
    private final String excel2007U = ".xlsx";//2007+ 版本的excel

    /**
     * 根据文件后缀，自适应上传文件的版本
     */
    private Workbook getWorkbook(InputStream inStr, String filename) throws Exception {
        Workbook workbook;
        String fileType = filename.substring(filename.lastIndexOf("."));
        if (excel2003L.equals(fileType)) {
            workbook = new HSSFWorkbook(inStr);//2003-
        } else if (excel2007U.equals(fileType)) {
            workbook = new XSSFWorkbook(inStr);//2007+
        } else {
            throw new Exception("解析的文件格式有误");
        }
        return workbook;
    }

//    /**
//     * 获取excel中的数据
//     */
//    public List<List<Object>> readExcelData(InputStream in, String filename) throws Exception {
//        List<List<Object>> list;
//
//        //创建Excel工作薄
//        Workbook work = getWorkbook(in, filename);
//        if (null == work) {
//            throw new Exception("创建Excel工作薄为空！");
//        }
//        Sheet sheet = null;
//        Row row = null;
//        Cell cell = null;
//
//        list = new ArrayList<List<Object>>();
//        //遍历Excel中所有的sheet
//        for (int i = 0; i < work.getNumberOfSheets(); i++) {
//            sheet = work.getSheetAt(i);
//            if (sheet == null) {
//                continue;
//            }
//
//            //遍历当前sheet中的所有行
//            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
//                row = sheet.getRow(j);
//                if (row == null || row.getFirstCellNum() == j) {
//                    continue;
//                }
//
//                //遍历所有的列
//                List<Object> li = new ArrayList<Object>();
//                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
//                    cell = row.getCell(y);
//                    //通过getCellValue方法获取当前行每一列中的数据
//                    li.add(getCellValue(cell));
//                }
//                //将每一行的数据添加到list
//                list.add(li);
//            }
//        }
//        in.close();
//        return list;
//    }
//
//    /**
//     * 获取每个单元格的内容
//     */
//    private Object getCellValue(Cell cell) {
//        Object value = null;
//
//        DecimalFormat df = new DecimalFormat("0");//格式化number String字符串
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//日期格式化
//
//        switch (cell.getCellType()) {
//            case Cell.CELL_TYPE_STRING:
//                value = cell.getRichStringCellValue().getString();
//                break;
//            case Cell.CELL_TYPE_NUMERIC:
//                if ("General".equals(cell.getCellStyle().getDataFormatString())) {
//                    value = df.format(cell.getNumericCellValue());
//                } else if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
//                    value = sdf.format(cell.getDateCellValue());
//                } else {
//                    value = cell.getNumericCellValue();
//                }
//                break;
//            case Cell.CELL_TYPE_BOOLEAN:
//                value = cell.getBooleanCellValue();
//                break;
//            case Cell.CELL_TYPE_BLANK:
//                value = "";
//                break;
//            default:
//                break;
//        }
//        return value;
//    }

//    /**
//     * 数据转成excel
//     *
//     * @param dataList   需要转换的数据
//     * @param sheetName  生成的excel表名
//     * @param columnName 每一列的名字(key)
//     * @return
//     */
//    public Workbook dataToExcel(List<Map<String, String>> dataList, String sheetName, String[] columnName) {
//
//        int columnNum = columnName.length;
//        Workbook workbook = new HSSFWorkbook();
//        //创建第一页，并命名
//        Sheet sheet = workbook.createSheet(sheetName);
//        //创建第一行
//        Row row = sheet.createRow(0);
//
//        // 创建两种单元格格式
//        CellStyle cs = workbook.createCellStyle();
//        CellStyle cs2 = workbook.createCellStyle();
//        // 创建两种字体
//        Font f = workbook.createFont();
//        Font f2 = workbook.createFont();
//        // 创建第一种字体样式（用于列名）
//        f.setFontHeightInPoints((short) 10);
//        f.setColor(IndexedColors.BLACK.getIndex());
//        f.setBoldweight(Font.BOLDWEIGHT_BOLD);
//
//        // 创建第二种字体样式（用于值）
//        f2.setFontHeightInPoints((short) 10);
//        f2.setColor(IndexedColors.BLACK.getIndex());
//
//
//        //设置列名
//        for (int i = 0; i < columnNum; i++) {
//            Cell cell = row.createCell(i);
//            cell.setCellValue(columnName[i]);
//            cell.setCellStyle(cs);
//        }
//        //设置每行每列的值
//        int rowNum = dataList.size();
//        for (int j = 1; j <= rowNum; j++) {
//            //创建一行
//            Row r = sheet.createRow(j);
//            for (int k = 0; k < columnNum; k++) {
//                Cell cell = r.createCell(k);
//                cell.setCellValue(dataList.get(j - 1).get(columnName[k]));
//                cell.setCellStyle(cs2);
//            }
//        }
//        return workbook;
//    }

    public static final ExcelUtil getInstance() {
        return ExcelUtilHolder.instance;
    }

    private static final class ExcelUtilHolder {
        private static final ExcelUtil instance = new ExcelUtil();
    }

    //获取学号-成绩 键-值对
    public Map<String,Double> readScoreExcel(InputStream in, String filename) throws Exception {
        Map<String,Double> map=new HashMap<>();

        //创建Excel工作薄
        Workbook work = getWorkbook(in, filename);
        if (null == work) {
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = work.getSheetAt(0);
        if (sheet == null) {
            return map;
        }
        Row row = null;
        Cell cell = null;

        //遍历当前sheet中的所有行
        for (int j = sheet.getFirstRowNum()+1; j <= sheet.getLastRowNum(); j++) {
            row = sheet.getRow(j);
            if (row == null) {
                continue;
            }

            Cell studentIdCell = row.getCell(0);
            studentIdCell.setCellType(CellType.STRING);
            Cell scoreCell = row.getCell(1);

            map.put(studentIdCell.getStringCellValue(),scoreCell.getNumericCellValue());

        }

        in.close();
        return map;
    }
}
