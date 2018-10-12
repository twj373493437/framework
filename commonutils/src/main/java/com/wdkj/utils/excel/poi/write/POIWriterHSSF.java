package com.wdkj.utils.excel.poi.write;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.Map;

/**
 * @Auther: TianWenjian
 * @Date: 2018/7/19 16:01
 * @Description:
 */
public class POIWriterHSSF extends AbstractPOIWriter {

    private HSSFWorkbook workbook;

    private HSSFSheet sheet;

    public POIWriterHSSF() {
        super();
        workbook = new HSSFWorkbook();
        sheet = workbook.createSheet("sheet1");
    }

    public POIWriterHSSF(HSSFWorkbook workbook) {
        super();
        this.workbook = workbook;
        sheet = workbook.getSheetAt(0);
    }

    @Override
    public Sheet getSheet() {
        return sheet;
    }

    @Override
    public void writeCell(int column, int row, Object value) throws Exception {

        HSSFRow hssfRow = sheet.getRow(row);
        if (hssfRow == null) {
            hssfRow = sheet.createRow(row);
        }
        HSSFCell cell = hssfRow.createCell(column);

        if (value == null) {
            cell.setCellValue("");
            return;
        }

        this.writeCell(cell, value);
    }

    @Override
    public Workbook getWorkbook() {
        return workbook;
    }
}
