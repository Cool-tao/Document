package org.alex.extension

/**
 * 作者：Alex
 * 时间：2017/7/25  18:22
 */
/**
 * ;{ queue.enqueueThrow("FF") }.tryNull { it.logW() }
 * */
inline fun <R> (() -> R).tryNull(catchBlock: (Exception) -> Unit): R? {
    try {
        return invoke()
    } catch (ex: Exception) {
        //ex.printStackTrace()
        catchBlock(ex)
        return null
    }
}

/**
 * ;{ queue.enqueueThrow("FF") }.tryDefault { it.logW() ; "hello"}
 * */
inline fun <R> (() -> R).tryDefault(catchBlock: (Exception) -> R): R {
    try {
        return invoke()
    } catch (ex: Exception) {
        //ex.printStackTrace()
        return catchBlock(ex)
    }
}

/**
 * tryOnly { queue.enqueueThrow("FF") }
 * */
inline fun <R> tryOnly(function: () -> R) {
    try {
        function.invoke()
    } catch (ex: Exception) {
    } finally {

    }
}