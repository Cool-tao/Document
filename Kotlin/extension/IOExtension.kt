package org.alex.extension

import java.io.File

/**
 * 作者：Alex
 * 时间：2017/7/27  10:47
 */
/**
 * 如果  目录 不存在， 先创建 目录， 在创建文件；
 * 如果 目录 或者 文件 存在， 对 原文件  不会造成 任何 影响
 */
class IOExtension {
    fun File.mkdirsNewFile(): File {
        this.absolutePath.logW()
        val parentFile = File(this.parent)
        if (!parentFile.exists()) {
            parentFile.mkdirs()
        }
        if (!this.exists()) {
            this.createNewFile()
        }
        return this
    }

}

inline fun <R> ioFun(function: IOExtension.() -> R): R = IOExtension().function()
