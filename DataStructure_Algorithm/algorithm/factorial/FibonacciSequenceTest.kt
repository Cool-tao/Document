package com.alex.structure.algorithm.factorial

import org.alex.util.LogTrack

/**
 * 作者：Alex
 * 时间：2017/9/22 23:03
 * 简述：菲波那切数列
 * 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233，377，610，987，1597，2584，4181，6765，10946，17711，28657，46368,  ...
 * 这个数列从第3项开始，每一项都等于前两项之和。
 */
fun main(args: Array<String>) {
    LogTrack.e(createFibonacciItem(10))
}

fun createFibonacciElement(size: Int): Int {
    if (size <= 1) {
        return 1
    }
    var left = 1
    var right = 1
    var result = left + right
    for (i in 2..size) {
        result = left + right
        left = right
        right = result
    }
    return result
}

fun createFibonacciItem(size: Int): Int {
    if (size <= 1) {
        return 1
    }
    return createFibonacciItem(size - 2) + createFibonacciItem(size - 1)
}

fun createFibonacciSequence(array: IntArray): IntArray {
    if (array.size == 1) {
        array[0] = 1
    }
    if (array.size >= 2) {
        array[0] = 1
        array[1] = 1
    }
    for (i in 2..array.size - 1) {
        array[i] = array[i - 2] + array[i - 1]
    }
    return array
}