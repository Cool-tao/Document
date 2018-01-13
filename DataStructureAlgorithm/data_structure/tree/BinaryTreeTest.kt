package com.alex.structure.data_structure.tree

import org.alex.extension.logW

/**
 * 作者：Alex
 * 时间：2017/10/14 11:43
 * 简述：
 */
fun main(args: Array<String>) {
    val tree = BinaryTree<String>()
    tree.insert("53")
    tree.insert("46")
    tree.insert("21")
    tree.insert("37")
    tree.insert("85")
    tree.insert("76")
    tree.inOrderAsList()?.joinToString(", ")?.logW()
    tree.remove("53")
    tree.inOrderAsList()?.joinToString(", ")?.logW()
}