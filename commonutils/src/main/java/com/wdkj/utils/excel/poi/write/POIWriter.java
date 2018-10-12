package com.wdkj.utils.excel.poi.write;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * @Auther: TianWenjian
 * @Date: 2018/7/19 15:42
 * @Description:
 */
public interface POIWriter {

    /**
     * 写入单行
     *
     * @param t
     */
    <T> void writeOneRow(T t, String[] columns, POIWriteHandler writeCellHandler) throws Exception;

    /**
     * 写入单行
     * 在一行中写入列表数据， 如果是数字，写数字，其他调用toString方法
     *
     * @param list
     */
    void writeListInOneRow(List list, POIWriteHandler writeCellHandler) throws Exception;

    /**
     * 写多行
     *
     * @param ts 数据
     */
    <T> void writeRows(List<T> ts, String[] columns, POIWriteHandler writeCellHandler) throws Exception;

    /**
     * @return 关闭之后并获取bytes数据
     * @throws Exception
     */
    byte[] closeAndGetBytes() throws Exception;

    /**
     * 写出
     * @param os
     */
    void write(OutputStream os) throws Exception;

    /**
     * 关闭
     */
    void close() throws Exception;

    /**
     * 下一行
     */
    void nextRow();

    /**
     * 替换占位符<br>
     * 占位符格式 ${xx}
     *
     * @param s
     * @param replacement
     */
     void replace(String s, String replacement);

    /**
     * 获取当前操作的行
     *
     * @return
     */
    int getSheetRowIndex();

    /**
     * 设置当前的行
     */
    void setSheetRowIndex(int index);


    Sheet getSheet();

    /**
     * 写单元格
     *
     * @param column
     * @param value
     * @throws Exception
     */
    void writeCell(int column, int row, Object value) throws Exception;

    /**
     * 获取workbook
     * @return
     */
    Workbook getWorkbook();

    /**
     * 合并
     * 网上说要先合并再写数据-----其实不用
     * @param row1 left top row
     * @param col1 left top col
     * @param row2 right bottom row
     * @param col2 right bottom col
     */
    void mergeCells(int row1, int col1, int row2, int col2);


}
