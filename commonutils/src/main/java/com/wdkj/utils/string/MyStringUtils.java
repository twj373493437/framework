package com.wdkj.utils.string;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author twj
 * @date 2018/9/19 20:08
 */
public class MyStringUtils {

    /**
     * 将参数 to  String
     * @param params
     * @return
     */
    public static String from(Map<String, String[]> params){
        StringBuilder builder = new StringBuilder();
        if (params != null){
            for (Map.Entry<String, String[]> entry : params.entrySet()){
                if (builder.length() > 0){
                    builder.append(",");
                }
                String[] values = entry.getValue();
                if (values != null && values.length > 1){
                    builder.append(entry.getKey()).append("=").append("[");
                    for (int i = 0; i < values.length; i++){
                        if (i == 0){
                            builder.append(values[i]);
                        }else{
                            builder.append("," + values[i]);
                        }
                    }
                    builder.append(entry.getKey()).append("=").append("]");
                }else if (values != null){
                    builder.append(entry.getKey()).append("=").append(values[0]);
                }
            }
        }
        return builder.toString();
    }

    /**
     * 更加分隔符转化为整型数组
     * @param source
     * @param separator
     * @return
     * @throws RuntimeException 和 NumberFormatException
     */
    public static List<Integer> toIntArray(String source, String separator){
        if (StringUtils.isNotBlank(source) && StringUtils.isNotEmpty(separator)){
            String[] strings = source.split(separator);
            List<Integer> integers = new ArrayList<>(strings.length);
            for(String s : strings){
                if (StringUtils.isNotBlank(s)){
                    integers.add(Integer.valueOf(s));
                }
            }
            return integers;
        }else {
            throw new RuntimeException("参数错误，无法解析,source:" + source + " separator:" + separator);
        }
    }
}
