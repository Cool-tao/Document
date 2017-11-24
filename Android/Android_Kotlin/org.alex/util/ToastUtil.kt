package org.alex.util

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.widget.Toast
import org.alex.widiget.SimpleToast


/**
 * 作者：Alex
 * 时间：2017/5/31 9:54
 * 简述：
 */
private val readMe = "必须在MainActivity的onDestory方法调用ToastUtil.doDestroy();回收资源"
private val gravityNone = -100
private var meiZuToast: SimpleToast? = null
fun Any.showToast(gravity: Int = Gravity.CENTER, duration: Int = Toast.LENGTH_SHORT) {
    if (isMainThread) {
        showOnMainThread(gravity, duration)
    } else {
        Handler(Looper.getMainLooper()).post({ showOnMainThread(gravity, duration) })
    }
}

/**
 * View操作必须工作在，主线程
 */
private fun Any.showOnMainThread(gravity: Int, duration: Int) {
    var text: String = this?.toString() ?: " "
    if (this is Int) {
        val string: String? = getString(this)
        text = if (isEmpty(string)) text else (string?.toString() ?: "")
    }
    meiZuToast = if (meiZuToast == null) SimpleToast.makeText(text, duration) else meiZuToast
    if (gravity == 17) {
        meiZuToast!!.setGravity(gravity, 0, -100)
    } else if (gravity == 48) {
        meiZuToast!!.setGravity(gravity, 0, 100)
    }
    meiZuToast!!.setText(text)
    meiZuToast!!.show()
}

/**
 * 判断 是 UI线程，还是子线程
 */
private val isMainThread: Boolean
    get() = Looper.myLooper() == Looper.getMainLooper()

fun Context.cancelToast() {
    if (isMainThread) {
        cancelOnMainThread()
    } else {
        Handler(Looper.getMainLooper()).post(Runnable { cancelOnMainThread() })
    }
}

private fun cancelOnMainThread() {
    if (meiZuToast != null) {
        meiZuToast!!.cancel()
    }
}

fun Activity.destroyToast() {
    if (meiZuToast != null) {
        meiZuToast!!.cancel()
    }
    meiZuToast = null
}

private fun getString(resId: Int): String? {
    val application = BaseUtil.application ?: return ""
    try {
        val string = application.getString(resId)
        return string?.toString() ?: ""
    } catch (ex: Exception) {
        //ex.logE()
    }
    return ""
}

