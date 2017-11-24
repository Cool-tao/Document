package org.alex.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * 作者：Alex
 * 时间：2017/6/2 15:30
 * 简述：
 */
/**
 * 数据转换: dp---->px
 */
fun Float.dp2px(): Int {

    if (context() == null) {
        return 0
    }
    return (this * context()!!.resources.displayMetrics.density).toInt()
}

fun layoutFrom(resource: Int, root: ViewGroup? = null, attachToRoot: Boolean = false): View
        = LayoutInflater.from(context()).inflate(resource, root, attachToRoot)

fun context(): Context? = BaseUtil.context
