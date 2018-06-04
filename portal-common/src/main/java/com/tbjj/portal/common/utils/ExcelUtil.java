package com.tbjj.portal.common.utils;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by handong on 2017/3/1.
 */
public class ExcelUtil {
    private final static String format =  "dd.MM.yyyy hh:mm:ss";

    public static void readXls(HSSFWorkbook hssfWorkbook,List<List<List<String>>> successList,List<List<List<String>>> failList,List<String> messageList) throws IOException {

        // Read the Sheet
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);

            List<List<String>> successRowList = new ArrayList<List<String>>();
            List<List<String>> failRowList = new ArrayList<List<String>>();
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                    Integer firstCellNum = new Integer(hssfRow.getFirstCellNum());
                    Integer lastCellNum  = new Integer(hssfRow.getLastCellNum());
                    boolean cellFlag =true;
                    List<String> cellList = new ArrayList<String>();
                    for(int cellNum = firstCellNum;cellNum<lastCellNum;cellNum++){
                        if(!checkCellType(hssfRow.getCell(cellNum),cellNum,messageList)){
                            cellFlag=false;
                        }
                        String value = getValue(hssfRow.getCell(cellNum),format);
                        cellList.add(value);
                        if(StringUtils.isEmpty(value)){
                            messageList.add("表格内容有空值");
                            cellFlag=false;
                        }
                    }
                    if(cellFlag){
                        successRowList.add(cellList);
                    }else{
                        failRowList.add(cellList);
                    }
                }
            }
        successList.add(successRowList);
        failList.add(failRowList);
    }

    public static void readXlsx(XSSFWorkbook xssfWorkbook,List<List<List<String>>> successList,List<List<List<String>>> failList,List<String> messageList) throws IOException {
        List<List<List<String>>> sheetList = new ArrayList<List<List<String>>>();
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
            // Read the Row
            List<List<String>> successRowList = new ArrayList<List<String>>();
            List<List<String>> failRowList = new ArrayList<List<String>>();
            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow != null) {
                    Integer firstCellNum = new Integer(xssfRow.getFirstCellNum());
                    Integer lastCellNum  = new Integer(xssfRow.getLastCellNum());
                    boolean cellFlag =true;
                    List<String> cellList = new ArrayList<String>();
                    for(int cellNum = firstCellNum;cellNum<lastCellNum;cellNum++){

                        if(!checkCellType(xssfRow.getCell(cellNum),cellNum,messageList)){
                            cellFlag=false;
                        }

                        String value = getValue(xssfRow.getCell(cellNum),format);
                        cellList.add(value);

                        if(StringUtils.isEmpty(value)){
                            messageList.add("表格内容有空值");
                            cellFlag=false;
                        }

                    }
                    if(cellFlag){
                        successRowList.add(cellList);
                    }else{
                        failRowList.add(cellList);
                    }
                }
            }
        successList.add(successRowList);
        failList.add(failRowList);
    }

    private static String getValue(XSSFCell cell,String format) {
        if (cell.getCellType() == XSSFCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                return DateFormatUtils.format(cell.getDateCellValue(), format);
            } else {
                return String.valueOf(cell.getNumericCellValue());
            }
        } else {
            return String.valueOf(cell.getStringCellValue());
        }
    }

    private static String getValue(HSSFCell cell,String format) {

        if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                return DateFormatUtils.format(cell.getDateCellValue(), format);
            } else {
                return String.valueOf(cell.getNumericCellValue());
            }
        } else {
            return String.valueOf(cell.getStringCellValue());
        }
    }

    private  static boolean checkCellType(XSSFCell cell,int cellNum,List<String> messageList){
        if(cellNum==0){
            if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
                return true;
            }
            messageList.add("A列格式异常");
        }else if(cellNum==1){
            if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
                return true;
            }
            messageList.add("B列格式异常");
        }else if(cellNum==2){
            if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    if(cell.getDateCellValue().compareTo(new Date())==1) {
                        messageList.add("生产日期超前");
                        return false;
                    }
                }
                return true;
            }
            messageList.add("C列格式异常");
        }else if(cellNum==3){
            if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                return true;
            }
            messageList.add("D列格式异常");
        }else if(cellNum==4){
            if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
                return true;
            }
            messageList.add("E列格式异常");
        }
        return false;
    }

    private  static boolean checkCellType(HSSFCell cell,int cellNum,List<String> messageList){
        if(cellNum==0){
            if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
                return true;
            }
            messageList.add("A列格式异常");
        }else if(cellNum==1){
            if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
                return true;
            }
            messageList.add("B列格式异常");
        }else if(cellNum==2){
            if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    if(cell.getDateCellValue().compareTo(new Date())==1) {
                        messageList.add("生产日期超前");
                        return false;
                    }
                }
                return true;
            }
            messageList.add("C列格式异常");
        }else if(cellNum==3){
            if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                return true;
            }
            messageList.add("D列格式异常");
        }else if(cellNum==4){
            if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
                return true;
            }
            messageList.add("E列格式异常");
        }
        return false;
    }
}
