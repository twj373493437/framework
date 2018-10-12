package com.wdkj.utils.excel.poi.read;

import java.util.List;
import java.util.Map;

/**
 * @Auther: TianWenjian
 * @Date: 2018/7/19 10:36
 * @Description:
 */
public interface POIReader {

    /**
     * 读为map
     *
     * @param sheetIndex
     * @param columns
     * @param startRow
     * @param handler
     * @return
     */
     List<Map<String, Object>> getMaps(int sheetIndex, String[] columns, int startRow, POIReaderCellHandler<Map<String, Object>> handler);
     
     
     /**
      * 动态读为map
      *
      * @param sheetIndex
      * @param columns
      * @param startRow
      * @param handler
      * @return
      */
     List<Map<String, Object>> dynMaps(int sheetIndex, String[] columns, int startRow, POIReaderCellHandler<Map<String, Object>> handler);

    /**
     * 读为实体
     *
     * @param sheetIndex
     * @param columns
     * @param startRow
     * @param clazz
     * @param handler
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> List<T> getEntities(int sheetIndex, String[] columns, int startRow, Class<T> clazz,
                                            POIReaderCellHandler<T> handler) throws Exception;

    /**
     * 获取某个单元格的值
     * @param row
     * @param col
     * @return
     */
    Object getCellValue(int row, int col);

    /**
     *
     * @param index
     */
    void setSheet(int index);

    /**
     * 用完之后记得关闭
     * @throws Exception 当关闭失败时
     */
    void close() throws Exception;
}
