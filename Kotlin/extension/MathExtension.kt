package org.alex.extension

import java.math.BigDecimal
import java.text.DecimalFormat

/**
 * 作者：Alex
 * 时间：2017/6/22 14:35
 * 简述：
 */

class MathExtension {

    /**
     * 向上取整，保留2为小数
     * */
    fun Double.ceil(length: Int = 2): String {
        val decimalFormat = DecimalFormat("#.${"0".repeat(length)}")
        if (this == 0.0) return "0.00"
        return (if (this < 1.00) "0" else "") + decimalFormat.format(Math.ceil(this))
    }

    /**
     * 仅仅  保存2位小数， 无论 第三位是什么 都丢掉
     */
    fun Int.decimalFormat(length: Int = 2): String {
        return "$this.00".decimalFormat(length)
    }

    /**
     * 仅仅  保存2位小数， 无论 第三位是什么 都丢掉
     */
    fun Long.decimalFormat(length: Int = 2): String {
        return "$this.00".decimalFormat(length)
    }

    /**
     * 仅仅  保存2位小数， 无论 第三位是什么 都丢掉
     */
    fun Double.decimalFormat(length: Int = 2): String {
        return this.toString().decimalFormat(length)
    }

    /**
     * 仅仅  保存2位小数， 无论 第三位是什么 都丢掉
     */
    fun String.decimalFormat(length: Int = 2): String {
        val tmp = this + "0".repeat(length)
        var indexDot = tmp.indexOf('.')
        if (indexDot <= 0) {
            indexDot = 0
        }
        return tmp.substring(0, indexDot + length + 1)
    }


    /**
     * 向上取整
     */
    fun Double.decimalDigitsRoundUp(length: Int = 2): Double {
        if (length < 0) {
            throw IllegalArgumentException("The scale must be a positive integer or zero")
        }
        val b = BigDecimal(java.lang.Double.toString(this))
        val one = BigDecimal("1")
        return b.divide(one, length, BigDecimal.ROUND_UP).toDouble()
    }
}

inline fun <R> mathFun(function: MathExtension.() -> R): R = MathExtension().function()

