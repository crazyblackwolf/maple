package com.zjf.utils.math;

import java.math.BigDecimal;

/**
 * @author : zhoujianfei
 * @description :
 * @date : 2017/11/20.
 */
public final class MathUtil {

    private MathUtil() {
        throw new AssertionError();
    }

    /**
     * 乘
     *
     * @param d1
     * @param d2
     * @return double
     */
    public static double mul(double d1, double d2) {
        BigDecimal num1 = BigDecimal.valueOf(d1);
        BigDecimal num2 = BigDecimal.valueOf(d2);
        num1 = num1.multiply(num2);
        return num1.doubleValue();
    }

    /**
     * 减
     *
     * @param d1
     * @param d2
     * @return double
     */
    public static double sub(double d1, double d2) {
        BigDecimal num1 = BigDecimal.valueOf(d1);
        BigDecimal num2 = BigDecimal.valueOf(d2);
        num1 = num1.subtract(num2);
        return num1.doubleValue();
    }

    /**
     * 加
     *
     * @param d1
     * @param d2
     * @return double
     */
    public static double add(double d1, double d2) {
        BigDecimal num1 = BigDecimal.valueOf(d1);
        BigDecimal num2 = BigDecimal.valueOf(d2);
        num1 = num1.add(num2);
        return num1.doubleValue();
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return double
     */
    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

}
