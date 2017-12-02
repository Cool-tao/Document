package org.alex.util;

import java.util.Arrays;

/**
 * 作者：Alex
 * 时间：2017/11/3010:25
 * 简述：
 */
@SuppressWarnings({"ImplicitArrayToString", "MismatchedReadAndWriteOfArray", "unchecked", "unused"})
public class ArrayUtil {

    /**
     * 数组，按照下标顺序合并，不作排序
     */
    public static <T> T[] concat(T firstArray[], T secondArray[]) {
        if (firstArray == null) {
            return secondArray;
        }
        if (secondArray == null) {
            return firstArray;
        }
        T[] result = Arrays.copyOf(firstArray, firstArray.length + secondArray.length);
        System.arraycopy(secondArray, 0, result, firstArray.length, secondArray.length);
        return result;
    }

    /**
     * 数组，按照下标顺序合并，不作排序
     */
    public static int[] concat(int firstArray[], int secondArray[]) {
        if (firstArray == null) {
            return secondArray;
        }
        if (secondArray == null) {
            return firstArray;
        }
        int[] result = Arrays.copyOf(firstArray, firstArray.length + secondArray.length);
        System.arraycopy(secondArray, 0, result, firstArray.length, secondArray.length);
        return result;
    }

    /**
     * 数组，按照下标顺序合并，不作排序
     */
    public static float[] concat(float firstArray[], float secondArray[]) {
        if (firstArray == null) {
            return secondArray;
        }
        if (secondArray == null) {
            return firstArray;
        }
        float[] result = Arrays.copyOf(firstArray, firstArray.length + secondArray.length);
        System.arraycopy(secondArray, 0, result, firstArray.length, secondArray.length);
        return result;
    }

    /**
     * 数组，按照下标顺序合并，不作排序
     */
    public static long[] concat(long firstArray[], long secondArray[]) {
        if (firstArray == null) {
            return secondArray;
        }
        if (secondArray == null) {
            return firstArray;
        }
        long[] result = Arrays.copyOf(firstArray, firstArray.length + secondArray.length);
        System.arraycopy(secondArray, 0, result, firstArray.length, secondArray.length);
        return result;
    }

    /**
     * 数组，按照下标顺序合并，不作排序
     */
    public static double[] concat(double firstArray[], double secondArray[]) {
        if (firstArray == null) {
            return secondArray;
        }
        if (secondArray == null) {
            return firstArray;
        }
        double[] result = Arrays.copyOf(firstArray, firstArray.length + secondArray.length);
        System.arraycopy(secondArray, 0, result, firstArray.length, secondArray.length);
        return result;
    }

    /**
     * 数组，按照下标顺序合并，不作排序
     */
    public static String[] concat(String firstArray[], String secondArray[]) {
        if (firstArray == null) {
            return secondArray;
        }
        if (secondArray == null) {
            return firstArray;
        }
        String[] result = Arrays.copyOf(firstArray, firstArray.length + secondArray.length);
        System.arraycopy(secondArray, 0, result, firstArray.length, secondArray.length);
        return result;
    }

    /**
     * 数组，按照下标顺序合并，不作排序
     */
    public static <T> T[] concatAll(T[] firstArray, T[]... rest) {
        int totalLength = firstArray.length;
        int restLength = 0;
        if (rest != null) {
            restLength = rest.length;
        }
        for (int i = 0; rest != null && i < restLength; i++) {
            T[] array = rest[i];
            if (array != null) {
                totalLength += array.length;
            }
        }
        T[] result = Arrays.copyOf(firstArray, totalLength);
        int offset = firstArray.length;
        for (int i = 0; rest != null && i < restLength; i++) {
            T[] array = rest[i];
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }
        return result;
    }
    /**
     * 数组，按照下标顺序合并，不作排序
     */
    public static int[] concatAll(int[] firstArray, int[]... rest) {
        int totalLength = firstArray.length;
        int restLength = 0;
        if (rest != null) {
            restLength = rest.length;
        }
        for (int i = 0; rest != null && i < restLength; i++) {
            int[] array = rest[i];
            if (array != null) {
                totalLength += array.length;
            }
        }
        int[] result = Arrays.copyOf(firstArray, totalLength);
        int offset = firstArray.length;
        for (int i = 0; rest != null && i < restLength; i++) {
            int[] array = rest[i];
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }
        return result;
    }
    /**
     * 数组，按照下标顺序合并，不作排序
     */
    public static float[] concatAll(float[] firstArray, float[]... rest) {
        int totalLength = firstArray.length;
        int restLength = 0;
        if (rest != null) {
            restLength = rest.length;
        }
        for (int i = 0; rest != null && i < restLength; i++) {
            float[] array = rest[i];
            if (array != null) {
                totalLength += array.length;
            }
        }
        float[] result = Arrays.copyOf(firstArray, totalLength);
        int offset = firstArray.length;
        for (int i = 0; rest != null && i < restLength; i++) {
            float[] array = rest[i];
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }
        return result;
    }
    /**
     * 数组，按照下标顺序合并，不作排序
     */
    public static long[] concatAll(long[] firstArray, long[]... rest) {
        int totalLength = firstArray.length;
        int restLength = 0;
        if (rest != null) {
            restLength = rest.length;
        }
        for (int i = 0; rest != null && i < restLength; i++) {
            long[] array = rest[i];
            if (array != null) {
                totalLength += array.length;
            }
        }
        long[] result = Arrays.copyOf(firstArray, totalLength);
        int offset = firstArray.length;
        for (int i = 0; rest != null && i < restLength; i++) {
            long[] array = rest[i];
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }
        return result;
    }
    /**
     * 数组，按照下标顺序合并，不作排序
     */
    public static double[] concatAll(double[] firstArray, double[]... rest) {
        int totalLength = firstArray.length;
        int restLength = 0;
        if (rest != null) {
            restLength = rest.length;
        }
        for (int i = 0; rest != null && i < restLength; i++) {
            double[] array = rest[i];
            if (array != null) {
                totalLength += array.length;
            }
        }
        double[] result = Arrays.copyOf(firstArray, totalLength);
        int offset = firstArray.length;
        for (int i = 0; rest != null && i < restLength; i++) {
            double[] array = rest[i];
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }
        return result;
    }
    /**
     * 数组，按照下标顺序合并，不作排序
     */    public static String[] concatAll(String[] firstArray, String[]... rest) {
        int totalLength = firstArray.length;
        int restLength = 0;
        if (rest != null) {
            restLength = rest.length;
        }
        for (int i = 0; rest != null && i < restLength; i++) {
            String[] array = rest[i];
            if (array != null) {
                totalLength += array.length;
            }
        }
        String[] result = Arrays.copyOf(firstArray, totalLength);
        int offset = firstArray.length;
        for (int i = 0; rest != null && i < restLength; i++) {
            String[] array = rest[i];
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }
        return result;
    }
}
