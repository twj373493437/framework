package com.wdkj.utils.excel.poi.write;

import com.wdkj.utils.reflection.ReflectionUtils;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * @Auther: TianWenjian
 * @Date: 2018/7/19 15:53
 * @Description:
 */
public abstract class AbstractPOIWriter implements POIWriter{

    private static final Logger log = LoggerFactory.getLogger(AbstractPOIWriter.class);

    /**
     * 行
     */
    private int rowIndex;

    AbstractPOIWriter(){

    }

    @Override
    public int getSheetRowIndex() {
        return rowIndex;
    }

    @Override
    public void setSheetRowIndex(int index) {
        this.rowIndex = index;
    }


    @Override
    public <T> void writeOneRow(T t, String[] columns, POIWriteHandler writeCellHandler) throws Exception{
        // 开始写入一行
        for (int col = 0; col < columns.length; col++) {
            Object value;
            if (t instanceof Map) {
                value = ((Map) t).get(columns[col]);
            } else {
                value = ReflectionUtils.getValueByFieldName(t, columns[col]);
            }

            if (writeCellHandler != null) {
                value = writeCellHandler.beforeWrite(getSheetRowIndex(), col, value);
            }

            if (value != null) {
                writeCell(col, getSheetRowIndex(), value);
            }

            if (writeCellHandler != null){
                writeCellHandler.afterWrite(getSheetRowIndex(), col, value);
            }
        }
    }

    @Override
    public void writeListInOneRow(List list, POIWriteHandler writeCellHandler) throws Exception {
        // 开始写入一行
        for (int col = 0; col < list.size(); col++) {
            Object value = list.get(col);
            if (writeCellHandler != null) {
                value = writeCellHandler.beforeWrite(getSheetRowIndex(), col, value);
            }

            if (value != null) {
                writeCell(col, getSheetRowIndex(), value);
            }

            if (writeCellHandler != null){
                writeCellHandler.afterWrite(getSheetRowIndex(), col, value);
            }
        }
    }

    @Override
    public <T> void writeRows(List<T> ts, String[] columns, POIWriteHandler writeCellHandler) throws Exception {
        for (T t : ts){
            this.writeOneRow(t, columns,  writeCellHandler);
            this.nextRow();
        }
    }

    @Override
    public void nextRow() {
        rowIndex++;
    }

    @Override
    public byte[] closeAndGetBytes() throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        getWorkbook().write(outputStream);
        byte[] bytes = outputStream.toByteArray();

        //其实ByteArrayOutputStream无需关闭，但是要保持习惯
        outputStream.close();
        getWorkbook().close();

        return bytes;
    }

    @Override
    public void write(OutputStream os) throws Exception{
        getWorkbook().write(os);
    }

    @Override
    public void close() throws Exception{
        getWorkbook().close();
    }

    @Override
    public void mergeCells(int row1, int col1, int row2, int col2) {

        //poi的规则 时 上下左右  我们的是 上左 下右
        getSheet().addMergedRegion(new CellRangeAddress(row1, row2, col1, col2));
        Row row = getSheet().getRow(row1);
        Cell cell = row.getCell(col1);

        CellStyle style = getWorkbook().createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        cell.setCellStyle(style);
    }

    /**
     * 写格子
     * @param cell
     * @param value
     */
    protected void writeCell(Cell cell, Object value){
        Class clazz = value.getClass();
        if (clazz.equals(Double.class) || clazz.equals(Float.class) || clazz.equals(Integer.class)) {
            cell.setCellType(CellType.NUMERIC);
            Double content = Double.valueOf(value.toString());
            cell.setCellValue(content);
        } else {
            cell.setCellType(CellType.STRING);
            cell.setCellValue(value.toString());
        }
    }

    @Override
    public void replace(String s, String replacement) {
        Sheet sheet = getSheet();
        int rows = sheet.getLastRowNum();

        //遍历row
        //遍历columns
        for (int i = 0; i < rows; i++){
            Row row = sheet.getRow(i);
            if (row != null){
                int columns = row.getLastCellNum();
                for (int j = 0; j < columns; j++ ){
                    Cell cell = row.getCell(j);
                    if (cell != null && cell.getCellType() == CellType.STRING){
                        String text = cell.getStringCellValue();
                        text = text.replace(s, replacement);
                        cell.setCellValue(text);
                        if (log.isDebugEnabled())log.debug("设置变量：" + s + "->" + replacement + "  " + text);
                    }
                }
            }
        }
    }
}
