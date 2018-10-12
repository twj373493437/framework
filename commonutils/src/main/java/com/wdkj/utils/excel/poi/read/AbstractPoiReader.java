package com.wdkj.utils.excel.poi.read;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;

import java.util.List;
import java.util.Map;

/**
 * @Auther: TianWenjian
 * @Date: 2018/7/19 10:12
 * @Description:
 */
public abstract class AbstractPoiReader implements POIReader{

    private Workbook workbook;
    private FormulaEvaluator evaluator;

    private Sheet sheet;

    AbstractPoiReader(Workbook workbook) {
        this.workbook = workbook;
        evaluator = workbook.getCreationHelper().createFormulaEvaluator();
    }

    /**
     * 获取单元格的值
     * @param cell
     * @return
     */
    Object getCellValue(Cell cell) {
        Object value = "";
        // 以下是判断数据的类型
        //todo 根据文档描述在poi4.0代码迁移之后 使用getCellType
        switch (cell.getCellTypeEnum()) {
            case NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    value = cell.getDateCellValue();
                } else {
                    value = cell.getNumericCellValue();
                }
                break;
            case STRING: // 字符串
                value = cell.getStringCellValue();
                break;
            case BOOLEAN: // Boolean
                value = cell.getBooleanCellValue();
                break;
            case FORMULA: // 公式
                CellValue cellValue = evaluator.evaluate(cell);
                value = getCellValue(cellValue);
                break;
            case BLANK: // 空值
                value = null;
                break;
            case ERROR: // 故障
                value = null;
                break;
            default:
                value = null;
                break;
        }
        return value;
    }

    /**
     * 总cellvalue这种获取值
     *
     * @param cell
     * @return
     */
    Object getCellValue(CellValue cell) {
        Object cellValue = null;
        switch (cell.getCellTypeEnum()) {
            case STRING:
                cellValue = cell.getStringValue();
                break;
            case NUMERIC:
                cellValue = cell.getNumberValue();
                break;
            default:
                break;
        }

        return cellValue;
    }

    public void setSheet(int index) {
        this.sheet = workbook.getSheetAt(index);
    }

    /**
     * 获取当前的sheet
     * @return
     */
    protected Sheet getCurSheet(){
        return this.sheet;
    }

    /**
     * 用完之后要关闭
     */
    public void close() throws Exception {
        if (workbook != null) {
            workbook.close();
        }
    }
}
