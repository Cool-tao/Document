package com.alex.structure.algorithm.least_common_multiple

import org.alex.util.LogTrack

/**
 * 作者：Alex
 * 时间：2017/10/7 16:46
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
    return num0 * num1 / left
}