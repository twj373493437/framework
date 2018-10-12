package com.wdkj.utils.excel.poi.read;

/**
 * @Auther: TianWenjian
 * @Date: 2018/7/19 10:37
 * @Description: 单元格值处理
 */
public interface POIReaderCellHandler<T> {

    /**
     * 处理
     *
     * @param key    键
     * @param value  值
     * @param row    行 excel 0 开始
     * @param column 同上
     * @return 值
     */
    Object handle(String key, Object value, int row, int column);

    /**
     * 处理行
     *
     * @param rowNum
     * @param row
     * @return if not null, row will be add to list
     */
    T handleRow(int rowNum, T row);
}
