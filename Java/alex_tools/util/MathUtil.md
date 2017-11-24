```
package org.alex.util;

import java.math.BigDecimal;

/**
 * 作者：Alex
 * 时间：2017/9/2 00:14
 * 简述：
 */
@SuppressWarnings({"WeakerAccess", "unused"})
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

    /**
     * 截取， 只展示 length 个小数点；如果 不够小数位，补 0
     */
    public static String decimalFormat(String sourceNum) {
        return decimalFormat(sourceNum, "0.00", 2);
    }

    /**
     * 截取， 只展示 length 个小数点；如果 不够小数位，补 0
     */
    public static String decimalFormat(String sourceNum, String defaultValue) {
        return decimalFormat(sourceNum, defaultValue, 2);
    }

    /**
     * 截取， 只展示 length 个小数点；如果 不够小数位，补 0
     */
    public static String decimalFormat(String sourceNum, String defaultValue, int length) {
        if (isEmpty(sourceNum)) {
            return defaultValue;
        }
        char firstChar = sourceNum.charAt(0);
        if (sourceNum.charAt(0) < '0' || firstChar > '9') {
            return defaultValue;
        }
        StringBuilder trailBuilder = new StringBuilder();
        for (int i = 0; i < length + 2; i++) {
            trailBuilder.append("0");
        }
        int indexDot = sourceNum.indexOf('.');
        if (indexDot >= 0) {
            sourceNum += trailBuilder.toString();
        }
        if (indexDot < 0) {
            indexDot = 1;
            sourceNum += ("." + trailBuilder.toString());
        }
        return sourceNum.substring(0, indexDot + length + 1);
    }

    public static double string2Double(Object sourceNum, double defaultValue) {
        double num = defaultValue;
        try {
            num = Double.valueOf(sourceNum.toString());
        } catch (Exception ignored) {

        }
        return num;
    }

    public static int string2Int(Object sourceNum, int defaultValue) {
        int num = defaultValue;
        try {
            num = Integer.valueOf(sourceNum.toString());
        } catch (Exception ignored) {

        }
        return num;
    }

    public static long string2Long(Object sourceNum, long defaultValue) {
        long num = defaultValue;
        try {
            num = Long.valueOf(sourceNum.toString());
        } catch (Exception ignored) {

        }
        return num;
    }

    public static float string2Float(Object sourceNum, float defaultValue) {
        float num = defaultValue;
        try {
            num = Float.valueOf(sourceNum.toString());
        } catch (Exception ignored) {

        }
        return num;
    }


    private static boolean isEmpty(Object text) {
        return text == null || text.toString().length() <= 0;
    }

    private static boolean isNotEmpty(Object text) {
        return !isEmpty(text);
    }
}

```