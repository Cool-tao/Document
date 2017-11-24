package org.alex.util

import android.content.Context
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * 作者：Alex
 * 时间：2017/6/7 15:09
 * 简述： var age: Int by Preference("age", 0)
 */
class Preference<T>(val name: String, val default: T, val sync: Boolean = false) : ReadWriteProperty<Any?, T> {
    val prefs by lazy { BaseUtil.context.getSharedPreferences("default", Context.MODE_PRIVATE) }
    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return findPreference(name, default)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        putPreference(name, value)
    }

    private fun <U> findPreference(name: String, default: U): U = with(prefs) {
        val res: Any = when (default) {
            is Long -> getLong(name, default)
            is Int -> getInt(name, default)
            is Boolean -> getBoolean(name, default)
            is Float -> getFloat(name, default)
            is Double -> getFloat(name, default.toFloat())
            else -> getString(name, default.toString())
        }
        res as U
    }

    private fun <U> putPreference(name: String, value: U) = with(prefs.edit()) {
        when (value) {
            is Long -> putLong(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            is Double -> putFloat(name, value.toFloat())
            else -> putString(name, value.toString())
        }.apply {
            if (sync) {
                this.commit()
            } else {
                this.apply()
            }
        }
    }
}