package org.alex.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 作者：Alex
 * 时间：2017/9/2 00:14
 * 简述：
 */
@java.lang.SuppressWarnings("SameParameterValue")
@SuppressWarnings({"WeakerAccess", "ResultOfMethodCallIgnored", "SameParameterValue"})
public class MathUtil {

    /**
     * 四舍五入，保留 小数点后 postDotLength 位；
     * 0.994 = 0.99
     * 0.995 = 1.00
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static Double roundHalfUp(String sourceNum, int postDotLength) {
        return new BigDecimal(sourceNum).divide(new BigDecimal(1), postDotLength, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 向上取整，保留 小数点后 postDotLength 位；
     * 0.994 = 1.00
     * 0.995 = 1.00
     */
    public static Double roundUp(String sourceNum, int postDotLength) {
        return new BigDecimal(sourceNum).divide(new BigDecimal(1), postDotLength, BigDecimal.ROUND_UP).doubleValue();
    }

    /**
     * 截取，保留 小数点后 postDotLength 位；
     * 0.994 = 0.99
     * 0.995 = 0.99
     */
    public static Double roundDown(String sourceNum, int postDotLength) {
        return new BigDecimal(sourceNum).divide(new BigDecimal(1), postDotLength, BigDecimal.ROUND_DOWN).doubleValue();
    }


    public static String decimalFormat(String sourceNum) {
        return decimalFormat(sourceNum, "0.00", 1, 2);
    }

    public static String decimalFormat(String sourceNum, String defaultValue) {
        return decimalFormat(sourceNum, defaultValue, 1, 2);
    }

    /**
     * 截取， 只展示 length 个小数点；如果 不够小数位，补 0
     */
    public static String decimalFormat(String sourceNum, String defaultValue, int postDotLength) {
        return decimalFormat(sourceNum, defaultValue, 1, postDotLength);
    }

    /**
     * 截取， 只展示 length 个小数点；如果 不够小数位，补 0
     * 1：new DecimalFormat("00.000").format(pi) //结果：03.142
     * 2：new DecimalFormat("##.###").format(pi) //结果：3.142
     */
    public static String decimalFormat(String sourceNum, String defaultValue, int preDotLength, int postDotLength) {
        double defaultValueDouble = string2Double(defaultValue, 0D);
        sourceNum = string2Double(sourceNum, defaultValueDouble) + "";
        double doubleValue = new BigDecimal(sourceNum).divide(new BigDecimal(1), postDotLength, BigDecimal.ROUND_DOWN).doubleValue();
        return new DecimalFormat(stringRepeat("0", preDotLength) + "." + stringRepeat("0", postDotLength)).format(doubleValue);
    }

    public static double string2Double(String sourceNum, double defaultValue) {
        double num = defaultValue;
        try {
            num = Double.valueOf(sourceNum);
        } catch (Exception ignored) {

        }
        return num;
    }

    public static int string2Int(String sourceNum, int defaultValue) {
        int num = defaultValue;
        try {
            num = Integer.valueOf(sourceNum);
        } catch (Exception ignored) {

        }
        return num;
    }

    private static String stringRepeat(String placeholder, int repeatCount) {
        StringBuilder builder = new StringBuilder(repeatCount + 2);
        for (int i = 0; i < repeatCount; i++) {
            builder.append(placeholder);
        }
        return builder.toString();
    }


    private static boolean isEmpty(Object text) {
        return text == null || text.toString().length() <= 0;
    }

    private static boolean isNotEmpty(Object text) {
        return !isEmpty(text);
    }
}
