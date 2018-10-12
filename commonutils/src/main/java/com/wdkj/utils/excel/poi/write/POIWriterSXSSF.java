package com.wdkj.utils.excel.poi.write;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.util.Map;

/**
 * @Auther: TianWenjian
 * @Date: 2018/7/23 15:00
 * @Description: 大量数据导出（内存可能不够用）时用这个，在这个时候不要使用getbytes方法
 */
public class POIWriterSXSSF extends AbstractPOIWriter {

    private SXSSFWorkbook sxssfWorkbook;
    private SXSSFSheet sxssfSheet;

    public POIWriterSXSSF(){
        sxssfWorkbook = new SXSSFWorkbook(500);
    }

    @Override
    public Sheet getSheet() {
        return sxssfSheet;
    }

    @Override
    public void writeCell(int column, int row, Object value) throws Exception {

        SXSSFRow hssfRow = sxssfSheet.getRow(row);
        if (hssfRow == null) {
            hssfRow = sxssfSheet.createRow(row);
        }
        SXSSFCell cell = hssfRow.createCell(column);

        if (value == null) {
            cell.setCellValue("");
            return;
        }

        this.writeCell(cell, value);
    }

    @Override
    public Workbook getWorkbook() {
        return sxssfWorkbook;
    }
}
