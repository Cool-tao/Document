package org.alex.util

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import org.json.JSONArray
import org.json.JSONObject
import java.util.*


/**
 * 作者：Alex
 * 时间：2017/5/31 10:23
 * 简述：
 */
/**
 * 对象为空
 */
fun isEmpty(obj: Any?): Boolean {
    if (obj == null) {
        return true
    }
    if (obj is String) {
        return obj.length == 0
    }
    if (obj is Map<*, *>) {
        return obj.isEmpty()
    }
    if (obj is Collection<*>) {
        return (obj as List<*>).isEmpty()
    }
    if (obj is Bitmap) {
        return isBitmapEmpty(obj as Bitmap?)
    }
    if (obj is Drawable) {
        return isDrawableEmpty(obj as Drawable?)
    }
    if (obj is JSONObject) {
        return obj.length() <= 0
    }
    if (obj is JSONArray) {
        return obj.length() <= 0
    }
    if (obj is android.util.SparseArray<*>) {
        return obj.size() == 0
    }
    if (obj is android.util.SparseIntArray) {
        return obj.size() == 0
    }
    if (obj is android.util.SparseBooleanArray) {
        return obj.size() == 0
    }
    if (obj is android.support.v4.util.SimpleArrayMap<*, *>) {
        return obj.size() == 0
    }
    if (obj is android.support.v4.util.LongSparseArray<*>) {
        return obj.size() == 0
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
        if (obj is android.util.LongSparseArray<*>) {
            return obj.size() <= 0
        }
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
        if (obj is android.util.SparseLongArray) {
            return obj.size() == 0
        }
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        if (obj is android.util.ArraySet<*>) {
            return obj.isEmpty()
        }
    }
    return obj.toString().length <= 0
}

/**
 * 对象非空
 */
fun isNotEmpty(obj: Any): Boolean {
    return !isEmpty(obj)
}

private fun isBitmapEmpty(bitmap: Bitmap?): Boolean {
    if (bitmap == null || bitmap.height <= 0 || bitmap.width <= 0) {
        return true
    }
    return false
}

private fun isDrawableEmpty(drawable: Drawable?): Boolean {
    if (drawable == null || drawable.intrinsicHeight <= 0 || drawable.intrinsicWidth <= 0) {
        return true
    }
    return false
}

fun toString(list: List<*>?, split: String): String {
    val builder = StringBuilder()
    builder.append("")
    var i = 0
    while (list != null && i < list.size) {
        if (i < list.size - 1) {
            builder.append(list[i].toString() + split)
        } else {
            builder.append(list[i].toString() + "")
        }
        i++
    }
    return builder.toString()
}

fun <T> toString(array: Array<T>): String {
    return Arrays.toString(array)
}


fun <T> toArray(list: List<T>?): Array<T> {
    if (list == null || list.size <= 0) {
        return arrayOfNulls<Any>(0) as Array<T>
    }
    val array = arrayOfNulls<Any>(list.size)
    var i = 0
    while (list != null && i < list.size) {
        array[i] = list[i].toString() + ""
        i++
    }
    return array as Array<T>
}

/**
 * 数组 转 List（实质上还是 ArayList）

 * @param t
 * *
 * @param <T>
 * *
 * @return
</T> */
fun <T> toList(vararg t: T): List<T> {
    if (t == null || t.size <= 0) {
        return ArrayList()
    }
    val list = ArrayList<T>()
    var i = 0
    while (t != null && i < t.size) {
        list.add(t[i])
        i++
    }
    return list
}

/**
 * 数组 转 ArrayList

 * @param t
 * *
 * @param <T>
 * *
 * @return
</T> */
fun <T> toArrayList(vararg t: T): ArrayList<T> {
    if (t == null || t.size <= 0) {
        return ArrayList()
    }
    val list = ArrayList<T>()
    var i = 0
    while (t != null && i < t.size) {
        list.add(t[i])
        i++
    }
    return list
}

/**
 * 是java 原生类 类型
 */
fun isJavaOriginalClass(obj: Any?): Boolean {
    if (obj is String) {
        return true
    }
    if (obj is Number) {
        return true
    }
    if (obj is Boolean) {
        return true
    }
    if (obj is Char) {
        return true
    }
    if (obj == null) {
        return true
    }
    return false
}

fun isNumber(clazz: Class<*>): Boolean {
    if (Boolean::class.java.simpleName.equals(clazz.simpleName, ignoreCase = true)) {
        return Boolean::class.javaPrimitiveType!!.isAssignableFrom(clazz)
    }
    if (Int::class.java.simpleName.equals(clazz.simpleName, ignoreCase = true)) {
        return Int::class.javaPrimitiveType!!.isAssignableFrom(clazz)
    }
    if (Double::class.java.simpleName.equals(clazz.simpleName, ignoreCase = true)) {
        return Double::class.javaPrimitiveType!!.isAssignableFrom(clazz)
    }
    if (Long::class.java.simpleName.equals(clazz.simpleName, ignoreCase = true)) {
        return Long::class.javaPrimitiveType!!.isAssignableFrom(clazz)
    }
    if (Float::class.java.simpleName.equals(clazz.simpleName, ignoreCase = true)) {
        return Float::class.javaPrimitiveType!!.isAssignableFrom(clazz)
    }
    if (Byte::class.java.simpleName.equals(clazz.simpleName, ignoreCase = true)) {
        return Byte::class.javaPrimitiveType!!.isAssignableFrom(clazz)
    }
    return false
}


/**
 * 从 第0个 开始 ，向右取length个（最多length个）
 */
fun <T> subList4Start(list: List<T>, length: Int): List<T> {
    return subList4StartLength(list, 0, length)
}

/**
 * 从 第startIndex个 开始 ，向右取length个（最多length个）
 */
fun <T> subList4StartLength(list: List<T>?, startIndex: Int, length: Int): List<T> {
    var startIndex = startIndex
    if (list == null || list.size <= 0) {
        return ArrayList()
    }
    if (list.size <= startIndex) {
        startIndex = list.size - 1
    }
    val subList = ArrayList<T>()
    var i = startIndex
    while (i < list.size && i <= length) {
        subList.add(list[i])
        i++
    }
    return subList
}

/**
 * 从 最后一个 开始 ，向左取length个（最多length个）
 */
fun <T> subList4End(list: List<T>?, length: Int): List<T> {
    //list.subList()
    if (list == null || list.size <= 0) {
        return ArrayList()
    }
    val subList = ArrayList<T>()
    // 0 1 2 3 4 5 6 7 8
    //8 7 6 5
    //9 - 5 >= 4
    var i = list.size - 1
    while (i >= 0 && list.size - i <= length) {
        subList.add(list[i])
        i--
    }
    return subList
}

/**
 * 从 第endIndex个 开始 ，向左取length个（最多length个）
 */
fun <T> subList4EndLength(list: List<T>?, endIndex: Int, length: Int): List<T> {
    var endIndex = endIndex
    //list.subList()
    if (list == null || list.size <= 0) {
        return ArrayList()
    }
    if (endIndex >= list.size) {
        endIndex = list.size - 1
    }
    val subList = ArrayList<T>()
    // 0 1 2 3 4 5 6 7 8
    //8 7 6 5
    //8 - 5 >= 4
    var i = endIndex
    while (i >= 0 && endIndex - i < length) {
        subList.add(list[i])
        i--
    }
    return subList
}

/**
 * copy一个完整的集合，得到一个ArrayList
 */
fun <T> subList4Full(list: List<T>?): List<T> {
    if (list == null || list.size <= 0) {
        return ArrayList()
    }
    val subList = ArrayList<T>()
    for (i in list.indices) {
        subList.add(list[i])
    }
    return subList
}

/**
 * 如果 startIndex 大于list.size 得到一个空集合
 * 如果 endIndex 大于list.size ，最多遍历到 list 结尾

 * @param list
 * *
 * @param startIndex 下标， 包含
 * *
 * @param endIndex   下标， 包含
 * *
 * @param <T>
 * *
 * @return
</T> */
fun <T> subList(list: List<T>?, startIndex: Int, endIndex: Int): List<T> {
    if (list == null || list.size <= 0 || startIndex > endIndex || startIndex >= list.size) {
        return ArrayList()
    }
    val subList = ArrayList<T>()
    var i = startIndex
    while (i <= endIndex && i < list.size - 1) {
        subList.add(list[i])
        i++
    }
    return subList
}


