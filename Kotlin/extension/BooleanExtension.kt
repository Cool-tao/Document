package org.alex.extension

/**
 * 作者：Alex
 * 时间：2017/6/21 17:09
 * 简述：
 */
sealed class BooleanExtension<out T> constructor(val boolean: Boolean)

object Otherwise : BooleanExtension<Nothing>(true)
class WithData<out T>(val data: T) : BooleanExtension<T>(false)

inline fun <T> Boolean.yes(block: () -> T): BooleanExtension<T> = when {
    this -> {
        WithData(block())
    }
    else -> Otherwise
}

inline fun <T> Boolean.no(block: () -> T) = when {
    this -> Otherwise
    else -> {
        WithData(block())
    }
}

inline infix fun <T> BooleanExtension<T>.otherwise(block: () -> T): T {
    return when (this) {
        is Otherwise -> block()
        is WithData<T> -> this.data
    }
}

inline operator fun <T> Boolean.invoke(block: () -> T) = yes(block)
