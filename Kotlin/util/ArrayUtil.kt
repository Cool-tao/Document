package org.alex.util

/**
 * 作者：Alex
 * 时间：2017/9/22 23:38
 * 简述：
 */
object ArrayUtil {
    fun intArrayOf(size: Int, elementDefaultValue: Int) = Array<Int>(size, { elementDefaultValue }).toIntArray()
    fun floatArrayOf(size: Int, elementDefaultValue: Float) = Array<Float>(size, { elementDefaultValue }).toFloatArray()
    fun doubleArrayOf(size: Int, elementDefaultValue: Double) = Array<Double>(size, { elementDefaultValue }).toDoubleArray()
}