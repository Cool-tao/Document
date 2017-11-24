package org.alex.extension

import java.text.SimpleDateFormat

/**
 * 作者：Alex
 * 时间：2017/7/27  17:22
 *
 */
private object TimeFormat {
    const val ALL = "yyyy-MM-dd HH:mm:ss"
    const val YEAR_MONTH = "yyyy-MM"
    const val YEAR_MONTH_DAY = "yyyy-MM-dd"
    const val HOUR_MINUTE_SECOND = "HH:mm:ss"
}

class SimpleDateFormatExtension {

    fun Long.formatTime(format: String = TimeFormat.ALL): String {
        val simpleDateFormat = SimpleDateFormat(format)
        return simpleDateFormat.format(this)
    }
}

inline fun <R> dateFun(function: SimpleDateFormatExtension.() -> R): R = SimpleDateFormatExtension().function()


