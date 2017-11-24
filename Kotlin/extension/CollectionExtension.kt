package org.alex.extension

/**
 * 作者：Alex
 * 时间：2017/6/27  11:39
 */
fun <T> List<T>.tail(): List<T> = this.drop(1)

infix fun <T> T.prependTo(list: List<T>): List<T> = listOf(this) + list

/**
 * 得到一个Pair
 * first  是 第一个元素
 * second  是 第一个元素以外的 所有元素 组成的 子集合
 */
fun <T> List<T>.destructured(): Pair<T, List<T>> = first() to tail()

/**
 * 从 startIndex  到 list 结束  的 子集合
 */
fun <T> List<T>.subListEnd(startIndex: Int): List<T> {
    var index = 0
    if (startIndex < 0) {
        index = 0
    }
    if (startIndex >= this.size) {
        return emptyList()
    }
    return this.subList(index, this.size - 1)
}

/**
 * 从 endIndex  到 list 开始  的 子集合
 */
fun <T> List<T>.subListStart(endIndex: Int): List<T> {
    var index = endIndex
    if (endIndex < 0) {
        index = 0
    }
    if (endIndex >= size) {
        index = size - 1
    }

    return this.subList(0, index)
}

/**
 * Returns a list containing elements at specified [indices].
 */
fun <T> List<T>.sliceOrEmpty(indices: Iterable<Int>): List<T> {
    val size = if (this is Collection<*>) this.size else 10
    if (size == 0) return emptyList()
    val list = ArrayList<T>(size)
    for (index in indices) {
        if (index >= 0 && index <= this.size - 1) {
            list.add(get(index))
        }
    }
    return list
}
