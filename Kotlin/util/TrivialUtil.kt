package org.alex.util

/**
 * 作者：Alex
 * 时间：2017/7/23 00:30
 * 简述：
 */
object TrivialUtil {

    /**
     * 如果 原 字符串 是 纯 En，
     * 输入 a 返回  B
     * 输入 y 返回  Z
     * ...
     * 输入 A 返回  B
     * 输入 Y 返回  Z
     * ...
     * 输入 Z 返回  ZA
     * 输入 ZY 返回  ZZ
     * ... * 代表 非a-z A-Z 的任意字符
     * 输入 * 返回  A
     * 输入 *** 返回  A
     */
    fun createNextText(currText: String?): String {
        if (currText == null || currText.isEmpty()) {
            return "A"
        }
        val length = currText.length
        val lastChar = currText[length - 1].toUpperCase()
        if (length == 1 && lastChar <= 'Y' && lastChar >= 'A') {
            return (lastChar + 1).toString()
        }
        if (length == 1 && lastChar > 'Z' || lastChar < 'A') {
            return "A"
        }
        if (length > 1 && lastChar <= 'Y' && lastChar >= 'A') {
            return currText.substring(0, length - 1) + (lastChar + 1).toString()
        }
        if (length > 1 && lastChar < 'A' || lastChar > 'Z') {
            return currText.substring(0, length - 1) + "A"
        }
        if (lastChar == 'Z') {
            return currText + "A";
        }
        return "A"
    }

}