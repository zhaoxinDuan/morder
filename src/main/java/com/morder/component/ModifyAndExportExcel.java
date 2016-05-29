package com.morder.component;

import com.morder.utils.FileUtil;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by amis on 16-5-29.
 */
@Component
public class ModifyAndExportExcel {
    public void modifyExcel(HttpServletResponse response, String filepath, String templatename, List<ExcelModel> modifyls, Integer userid) throws Exception {
        String fromfile = filepath + templatename;
        String tofile = filepath +  "temp" + File.separator + userid + String.valueOf(System.currentTimeMillis()).substring(4, 13) + ".xls";
        FileUtil.copyFile(fromfile, tofile);
        FileInputStream fis = null;
        HSSFWorkbook workbook = null;
        HSSFSheet sheet = null;
        HSSFCell cell = null;
        try {
            fis = new FileInputStream(new File(tofile));
            workbook = new HSSFWorkbook(fis);
            sheet = workbook.getSheetAt(0);
            for (ExcelModel excelModel : modifyls) {
                cell = sheet.getRow(excelModel.getRownum()).getCell(excelModel.getCellnum());
                cell.setCellValue(excelModel.getValue());
            }

        } catch (IOException e) {
            throw e;
        } catch (EncryptedDocumentException e) {
            throw e;
        } finally {
            if (fis != null) fis.close();
        }

        if (workbook != null) {
            try {
                String fileName = userid + String.valueOf(System.currentTimeMillis()).substring(4, 13) + ".xls";
                String headStr = "attachment; filename=\"" + fileName + "\"";
                response.setContentType("APPLICATION/OCTET-STREAM");
                response.setHeader("Content-Disposition", headStr);
                OutputStream out = response.getOutputStream();
                workbook.write(out);
            } catch (IOException e) {
                throw e;
            }
        }

    }
}
