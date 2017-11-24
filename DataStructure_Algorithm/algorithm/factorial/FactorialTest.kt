package com.alex.structure.algorithm.factorial

import org.alex.util.LogTrack

/**
 * 作者：Alex
 * 时间：2017/9/23 00:19
 * 简述： 递归 计算 N 的阶乘
 */
fun main(args: Array<String>) {

    LogTrack.e(factorial(4))

}

fun factorial(n: Int): Long {
    if (n <= 1) {
        return 1.toLong()
    }
    return n * factorial(n - 1)
}

