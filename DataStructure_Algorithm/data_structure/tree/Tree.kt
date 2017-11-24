package com.alex.structure.data_structure.tree

import com.alex.structure.data_structure.datamodel.HuffmanNode

/**
 * 作者：Alex
 * 时间：2017/10/14 09:11
 * 简述：
 */
interface Tree<in T : Comparable<T>> {
    /**插入节点*/
    fun insert(data: T)

    /**移除节点*/
    fun remove(data: T): Boolean

}

interface BinTree<in T : Comparable<T>> : Tree<T> {
    /**先序遍历*/
    fun preOrderAsList(): List<Comparable<T>>?

    /**中序遍历*/
    fun inOrderAsList(): List<Comparable<T>>?

    /**后序遍历*/
    fun postOrderAsList(): List<Comparable<T>>?

}

interface HuffTree  {

}