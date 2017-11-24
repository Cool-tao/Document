package org.alex.extension

import com.google.gson.Gson

/**
 * 作者：Alex
 * 时间：2017/5/27  10:10
 */
inline fun <reified T: Any> Gson.fromJson(json:String): T = this.fromJson(json, T::class.java)