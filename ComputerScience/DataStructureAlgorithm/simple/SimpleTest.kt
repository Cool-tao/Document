package com.alex.structure.simple

import org.alex.util.LogTrack
import org.alex.util.MathUtil

/**
 * 作者：Alex
 * 时间：2017/9/20 22:53
 * 简述：
 */
fun main(args: Array<String>) {
    val intArrayOf = intArrayOf(44, 33, 40, 22, 10, 82, 10, 9, 8, 10, 20)

    LogTrack.w("最大值 = " + searchMaxValue(intArrayOf))
    LogTrack.w("最小值 = " + searchMinValue(intArrayOf))
    LogTrack.w("平均值 = " + MathUtil.decimalFormat(averageValue(intArrayOf).toString(), 2, "0.00"))

}

fun averageValue(array: IntArray): Float {
    if (array.size == 1) {
        return array[0].toFloat()
    }
    var amount = array[0]
    for (i in 1..array.size - 1) {
        amount += array[i]
    }
    return amount.toFloat() / array.size
}


fun searchMinValue(array: IntArray): Int {
    if (array.size == 1) {
        return array[0]
    }
    var min = array[0]
    for (i in 1..array.size - 1) {
        min = if (min < array[i]) min else array[i]
    }
    return min
}

fun searchMaxValue(array: IntArray): Int {
    if (array.size == 1) {
        return array[0]
    }
    var max = array[0]
    for (i in 1..array.size - 1) {
        max = if (max > array[i]) max else array[i]
    }
    return max
}
