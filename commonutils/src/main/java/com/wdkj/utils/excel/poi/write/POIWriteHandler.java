package com.wdkj.utils.excel.poi.write;

/**
 * @Auther: TianWenjian
 * @Date: 2018/7/19 15:51
 * @Description:
 */
public interface POIWriteHandler {

    /**
     * 写一个格子之前
     *
     * @return content  if return null, 则不写入这个格子
     */
    Object beforeWrite(int row, int col, Object value);

    /**
     * 写入之后调用
     *
     * @param row
     * @param col
     */
    void afterWrite(int row, int col, Object value);
}
