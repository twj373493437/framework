package com.wdkj.utils.excel.poi.write;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.util.List;
import java.util.Map;

/**
 * @Auther: TianWenjian
 * @Date: 2018/7/19 16:01
 * @Description:
 */
public class POIWriterXSSF extends AbstractPOIWriter{

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    // 创建一个空的Excel表格
    public POIWriterXSSF() throws Exception {
        super();
        // 创建空的
        this.workbook = new XSSFWorkbook();
        sheet = workbook.getSheetAt(0);

        this.setSheetRowIndex(0);
    }

    public POIWriterXSSF(XSSFWorkbook workbook) {
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

        XSSFRow hssfRow = sheet.getRow(row);
        if (hssfRow == null) {
            hssfRow = sheet.createRow(row);
        }
        XSSFCell cell = hssfRow.createCell(column);

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
