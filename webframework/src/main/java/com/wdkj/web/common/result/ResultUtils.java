package com.wdkj.web.common.result;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: TianWenjian
 * @Date: 2018/7/11 20:48
 * @Description: 接口返回数据工具类
 */
public class ResultUtils {

    public static final int CODE_SUCCESS = 1;
    public static final int DEFAULT_CODE_FAILED = -1;

    public static final String CODE = "code";
    public static final String ROWS = "rows";
    public static final String MESSAGE = "message";

    /**
     * 包装成功的结果
     * @param message 信息
     * @param rows 数据
     * @return 结果
     */
    public static Map succeed(String message, Object rows){
        Map<String, Object> map = new HashMap<>(8);
        map.put(CODE, CODE_SUCCESS);
        map.put(ROWS, rows);
        map.put(MESSAGE, message);

        return map;
    }

    /**
     * 包装成功的结果<br></br>
     *  在方法重载时，Object是String的父类,所以在传递参数时 message 声明的引用类型必须是 {@link String}(他不会有子类)
     *  才能被编译器链接到 参数为String message 的方法上.
     *  另外当参数为null时，如果存在继承性（不存在时报错，因为编译器也不能决定是哪个），编译器选取最有精确性的那个，在这里是String而不是Object
     * @param message 信息
     * @return 结果
     */
    public static Map succeed(String message){
        Map<String, Object> map = new HashMap<>(8);
        map.put(CODE, CODE_SUCCESS);
        map.put(MESSAGE, message);

        return map;
    }

    /**
     * 包装成功的结果<br>
     *
     * @param rows 数据
     * @return 结果
     */
    public static Map succeed(Object rows){
        Map<String, Object> map = new HashMap<>(8);
        map.put(CODE, CODE_SUCCESS);
        map.put(ROWS, rows);

        return map;
    }

    public static Map succeed(String message, Object rows, Integer total){
        Map<String, Object> map = new HashMap<>(8);
        map.put(CODE, CODE_SUCCESS);
        map.put(MESSAGE, message);
        map.put(ROWS, rows);
        map.put("total", total);
        return map;
    }

    /**
     * 包装失败的结果
     * @param message 信息
     * @return 结果
     */
    public static Map fail(String message){
        Map<String, Object> map = new HashMap<>(8);
        map.put(CODE, DEFAULT_CODE_FAILED);
        map.put(MESSAGE, message);

        return map;
    }
}
