package com.alex.structure

import org.alex.util.ArrayUtil
import org.alex.util.LogTrack

/**
 * 作者：Alex
 * 时间：2017/9/20 21:44
 * 简述：
 */
fun main(args: Array<String>) {

    val array = ArrayUtil.stringArrayOf(10, "AA")
    LogTrack.w(ArrayUtil.intArrayOf(10, -1)::class.java)
    LogTrack.w(ArrayUtil.floatArrayOf(10, -1F)::class.java)
    LogTrack.w(ArrayUtil.doubleArrayOf(10, -1.0)::class.java)
    LogTrack.w(ArrayUtil.stringArrayOf(10, "-1")::class.java)
}
