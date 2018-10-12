package com.wdkj.utils.excel.poi.read;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: TianWenjian
 * @Date: 2018/7/19 10:38
 * @Description:
 */
public class POIReaderXSSF extends AbstractPoiReader {

    private XSSFWorkbook workbook;

    public POIReaderXSSF(XSSFWorkbook workbook) {
        super(workbook);
        this.workbook = workbook;
    }

    /**
     * 读数据
     *
     * @param sheetIndex sheet, first is 0
     * @param columns    对应Excel列顺序的map key
     * @param startRow   开始行
     * @param handler    单元格数据处理器
     * @return map 集合
     */
    public List<Map<String, Object>> getMaps(int sheetIndex, String[] columns, int startRow, POIReaderCellHandler<Map<String, Object>> handler) {
        // 指定sheet
        XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
        // 行数
        int rowCounts = sheet.getLastRowNum() + 1;
        List<Map<String, Object>> list = new ArrayList<>(rowCounts);
        

        // 双重for循环取出数据
        for (int row = startRow; row < rowCounts; row++) {
            Map<String, Object> rowMap = new HashMap<>();
            for (int col = 0; col < columns.length; col++) {
                Object value = this.getCellValue(row, col);
                value = handler == null ? value : handler.handle(columns[col], value, row, col);
                if (value != null) rowMap.put(columns[col], value);
            }

            list.add(rowMap);
        }

        return list;
    }

    /**
     * 读数据
     *
     * @param sheetIndex sheet
     * @param columns    对应Excel列顺序的field name
     * @param startRow   开始行
     * @param clazz      要导出的数据实体类型
     * @param handler    数据处理器，此处必须自定义一个，定义字符串数据如何转换成实体变量
     * @return 实体集合
     * @throws Exception when have exception
     */
    public <T> List<T> getEntities(int sheetIndex, String[] columns, int startRow, Class<T> clazz,
                                   POIReaderCellHandler<T> handler) throws Exception {
        // 指定sheet
        XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
        // 行数
        int rowCounts = sheet.getLastRowNum() + 1;
        List<T> list = new ArrayList<>(rowCounts);

        // 双重for循环取出数据
        for (int row = startRow; row < rowCounts; row++) {
            T t = clazz.newInstance();
            for (int col = 0; col < columns.length; col++) {
                Object value = this.getCellValue(row, col);
                if (handler != null) {
                    value = handler.handle(columns[col], value, row, col);
                }
                if (value != null) BeanUtils.setProperty(t, columns[col], value);
            }

            if (handler != null) {
                t = (T) handler.handleRow(row, t);
            }

            if (t != null) {
                list.add(t);
            }
        }
        return list;
    }

    @Override
    public Object getCellValue(int row, int col) {
        XSSFRow xssfRow = ((XSSFSheet) getCurSheet()).getRow(row);
        XSSFCell cell = xssfRow == null ? null : xssfRow.getCell(col);
        return cell == null ? null : this.getCellValue(cell);
    }

	@Override
	 
    /**
     * 动态读数据
     *
     * @param sheetIndex sheet, first is 0
     * @param columns    对应Excel列顺序的map key
     * @param startRow   开始行
     * @param handler    单元格数据处理器
     * @return map 集合
     *@Auther: liaokewu
     */
    public List<Map<String, Object>>  dynMaps(int sheetIndex, String[] columns, int startRow, POIReaderCellHandler<Map<String, Object>> handler) {
    	// 指定sheet
    	XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
    	// 行数
    	int rowCounts = sheet.getLastRowNum() + 1;
    	List<Map<String, Object>> list = new ArrayList<>(rowCounts);
    	
    	int colsNum = 0;
    	Map<String,Object>map = new HashMap<>();
    	XSSFRow xRow  = sheet.getRow(0);
    	XSSFCell hCell1 = xRow.getCell(1);
    	if(hCell1 == null || hCell1.getCellType() == CellType.BLANK){
    		map.put("总列数", 1);
    		list.add(map);
    	}else{
    		for(Cell cell:xRow){
    			if(cell != null && cell.getCellType() != CellType.BLANK){
    				colsNum++;
    			}else{
    				map.put("总列数", colsNum);
    				list.add(map);
    				break;
    			}
    		}
    	}
    	
    	// 双重for循环取出数据
    	for (int row = startRow; row < rowCounts; row++) {
    		Map<String, Object> rowMap = new HashMap<>();
    		for (int col = 0; col < columns.length; col++) {
    			XSSFRow hssfRow = sheet.getRow(row);
    			XSSFCell cell = hssfRow == null ? null : hssfRow.getCell(col);
    			Object value = cell == null ? null : this.getCellValue(cell);
    				
    			value = handler == null ? value : handler.handle(columns[col], value, row, col);
    			if (value != null) rowMap.put(columns[col], value);
    		}
    		
    		if (handler != null) {
    			rowMap = handler.handleRow(row, rowMap);
    		}
    		
    		if (rowMap != null) {
    			list.add(rowMap);
    		}
    	}
    	
    	return list;
    }
    
}
