package com.alex.structure.algorithm.greatest_common_divisor

import org.alex.util.LogTrack

/**
 * 作者：Alex
 * 时间：2017/10/7 16:43
 * 简述：
 */

fun main(args: Array<String>) {

    LogTrack.w(gcd(377, 319))

}

/**
 * 辗转相除法 or 欧几里德算法
 * */
fun gcd(num0: Int, num1: Int): Int {
    var tmp: Int
    var left = num0
    var right = num1
    while (right > 0) {
        tmp = left % right
        left = right
        right = tmp
    }
    return left
}