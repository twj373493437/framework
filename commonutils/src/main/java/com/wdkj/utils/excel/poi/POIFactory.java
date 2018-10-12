package com.wdkj.utils.excel.poi;
import com.wdkj.utils.excel.poi.read.POIReader;
import com.wdkj.utils.excel.poi.read.POIReaderHSSF;
import com.wdkj.utils.excel.poi.read.POIReaderXSSF;
import com.wdkj.utils.excel.poi.write.POIWriter;
import com.wdkj.utils.excel.poi.write.POIWriterHSSF;
import com.wdkj.utils.excel.poi.write.POIWriterXSSF;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

/**
 * @Auther: TianWenjian
 * @Date: 2018/7/19 10:36
 * @Description: POI现在支持 Excel2007 所以需要工厂类
 */
public class POIFactory {

    public static final String TYPE_XLSX = "xlsx";
    public static final String TYPE_XLS = "xls";

    /**
     * 从输入流中获取一个reader
     * @param type
     * @param stream
     * @return
     * @throws Exception
     */
    public static POIReader getReader(String type, InputStream stream) throws Exception{
        switch (type) {
            case TYPE_XLS:
                return new POIReaderHSSF(new HSSFWorkbook(stream));
            case TYPE_XLSX:
                return new POIReaderXSSF(new XSSFWorkbook(stream));
            default:
                throw new Exception("无法打开的类型");
        }
    }

    /**
     * 获取一个空的POI写出器
     * @param type
     * @return
     */
    public static POIWriter getWriter(String type) throws Exception{
        switch (type) {
            case TYPE_XLS:
                POIWriterHSSF poiWriterHSSF = new POIWriterHSSF();
                return poiWriterHSSF;
            case TYPE_XLSX:
                POIWriterXSSF poiWriterXSSF = new POIWriterXSSF();
                return poiWriterXSSF;
            default:
                throw new Exception("无法打开的类型");
        }
    }

    /**
     * 获取一个空的POI写出器
     * @param template 指定写入序列名
     * @param type
     * @return
     */
    public static POIWriter getWriter(String type, File template) throws Exception{

        //先读出所有字节
        ByteArrayInputStream byteInputStream = new ByteArrayInputStream(FileUtils.readFileToByteArray(template));

        switch (type) {
            case TYPE_XLS:
                return new POIWriterHSSF(new HSSFWorkbook(byteInputStream));
            case TYPE_XLSX:
                return new POIWriterXSSF(new XSSFWorkbook(byteInputStream));
            default:
                throw new Exception("无法打开的类型");
        }
    }
}
