package com.wdkj.utils.number;

import java.math.BigDecimal;

/**
 * @author twj
 * @date 2018/9/19 15:02
 */
public class NumberUtils {

    /**
     * 四舍五入保留指定的小数位
     * @param value
     * @param precision
     * @return
     */
    public static double setScale(double value, int precision){
        BigDecimal b = new BigDecimal(value);
        return b.setScale(precision, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     *
     * @param value
     * @return
     */
    public static String toPercent(double value){
        return (value * 100) + "%";
    }
}
