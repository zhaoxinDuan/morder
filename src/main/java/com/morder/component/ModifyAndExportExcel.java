package com.morder.component;

import com.morder.utils.FileUtil;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.*;
;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * Created by amis on 16-5-29.
 */
@Component
public class ModifyAndExportExcel {
    @Autowired
    private TemplateConfig templateConfig;

    public void modifyExcel(HttpServletResponse response, String filepath, String templatename, List<List<ExcelModel>> lists, Integer userid, String num) throws Exception {
        String fromfile = filepath + templatename;
        String tofile = templateConfig.getExecltemplatetemppath() + userid + String.valueOf(System.currentTimeMillis()).substring(4, 13) + ".xls";
        FileUtil.copyFile(fromfile, tofile);
        FileInputStream fis = null;
        HSSFWorkbook workbook = null;
        HSSFSheet sheet = null;
        HSSFCell cell = null;
        List<ExcelModel> modifyls = null;
        HSSFCellStyle cellStyle = null;
        String value = null;

        try {
            fis = new FileInputStream(new File(tofile));
            workbook = new HSSFWorkbook(fis);


            HSSFFont font = workbook.createFont();
            font.setFontHeightInPoints((short)8); //字体大小
            font.setFontName("宋体");
            for (int i = 0; i < lists.size(); i++) {
                if (i > 0) {
                    sheet = workbook.cloneSheet(0);
                }
                sheet = workbook.getSheetAt(i);

                modifyls = lists.get(i);

                workbook.setSheetName(i, modifyls.get(4).getValue() + "-" + i);

                for (ExcelModel excelModel : modifyls) {
                    value = excelModel.getValue();
                    cell = sheet.getRow(excelModel.getRownum()).getCell(excelModel.getCellnum());
                    if (excelModel.iscellstyle()) {
                        cellStyle = workbook.createCellStyle();
                        cellStyle.setWrapText(true);
                        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
                        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
                        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
                        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
                        cell.setCellStyle(cellStyle);
                    }

                    if(excelModel.isAnicelllength()){
                        if(value.length()>excelModel.getMaxsize()){
                            cellStyle = workbook.createCellStyle();
                            cellStyle.setWrapText(true);
                            cellStyle.setFont(font);
                            cell.setCellStyle(cellStyle);
                            if(value.length()>excelModel.getSubsize()){
                                value = value.substring(0, excelModel.getSubsize());
                            }
                        }
                    }
                    cell.setCellValue(value);
                }

            }

        } catch (IOException e) {
            throw e;
        } catch (EncryptedDocumentException e) {
            throw e;
        } finally {
            if (fis != null) fis.close();
        }

//        //让列宽随着导出的列长自动适应
//        for (int colNum = 0; colNum < lists.size(); colNum++) {
//            int columnWidth = sheet.getColumnWidth(colNum) / 256 + 1;
//            for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
//                HSSFRow currentRow;
//                //当前行未被使用过
//                if (sheet.getRow(rowNum) == null) {
//                    currentRow = sheet.createRow(rowNum);
//                } else {
//                    currentRow = sheet.getRow(rowNum);
//                }
//
//                if (currentRow != null && currentRow.getCell(colNum) != null) {
//                    HSSFCell currentCell = currentRow.getCell(colNum);
//                    if (currentCell != null && currentCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
//                        int length = currentCell.getStringCellValue().getBytes().length;
//                        if (columnWidth < length) {
//                            columnWidth = length;
//                        }
//                    }
//                }
//
//            }
//            if (colNum == 0) {
//                sheet.setColumnWidth(colNum, (columnWidth - 2) * 256 + 1);
//            } else {
//                sheet.setColumnWidth(colNum, (columnWidth + 4) * 256 + 1);
//            }
//        }
        if (workbook != null) {
            String savefilepath = filepath;
            File file = null;
            if (templatename.indexOf("ptemplate") > -1) {
                savefilepath = savefilepath + "ptemplate/" + num + "_" + userid + ".xls";
            } else {
                savefilepath = savefilepath + "stemplate/" + num + "_" + userid + ".xls";
            }
            file = new File(savefilepath);


            FileOutputStream fos = new FileOutputStream(file);
            try {
                String fileName = num + "_" + userid + ".xls";
                String headStr = "attachment; filename=\"" + fileName + "\"";
                response.setContentType("APPLICATION/OCTET-STREAM");
                response.setHeader("Content-Disposition", headStr);
                OutputStream out = response.getOutputStream();
                workbook.write(out);
                workbook.write(fos);
            } catch (IOException e) {
                throw e;
            } finally {
                if (workbook != null) workbook.close();
                if (fos != null) fos.close();
            }
        }

    }
}
