@file:Suppress("unused")

package org.alex.extension

import android.util.Log

/**
 * 作者：Alex
 * 时间：2017/7/19 11:59
 * 简述：
 */


fun Any.logV() = printLog(false, Log.VERBOSE, this)

fun Any.logV(isPrintTrack: Boolean) = printLog(isPrintTrack, Log.VERBOSE, this)
fun Any.logD() = printLog(false, Log.DEBUG, this)
fun Any.logD(isPrintTrack: Boolean) = printLog(isPrintTrack, Log.DEBUG, this)
fun Any.logI() = printLog(false, Log.INFO, this)
fun Any.logI(isPrintTrack: Boolean) = printLog(isPrintTrack, Log.INFO, this)
fun Any.logW() = printLog(false, Log.WARN, this)
fun Any.logW(isPrintTrack: Boolean) = printLog(isPrintTrack, Log.WARN, this)
fun Any.logE() = printLog(false, Log.ERROR, this)
fun Any.logE(isPrintTrack: Boolean) = printLog(isPrintTrack, Log.ERROR, this)
fun Any.printLog(logLevel: Int, stackIndex: Int) = LogTrack.printLog(this, logLevel, stackIndex, false)

@Suppress("SENSELESS_COMPARISON")
private fun printLog(isPrintTrack: Boolean, logLevel: Int, text: Any) {
    if (text == null) {
        return
    }
    LogTrack.printLog(text, logLevel, 6, isPrintTrack)
}


