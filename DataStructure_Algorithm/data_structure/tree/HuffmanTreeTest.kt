package com.alex.structure.data_structure.tree

import com.alex.structure.data_structure.datamodel.HuffmanNode
import org.alex.extension.SortUtil
import org.alex.extension.logE
import org.alex.extension.logW
import org.alex.util.LogTrack

/**
 * 作者：Alex
 * 时间：2017/10/1915:25
 * 简述：
 */
fun main(args: Array<String>) {
    val tree = HuffmanTree()
    val nodeArray = arrayOf(
            HuffmanNode("A", 9),
            HuffmanNode("B", 12),
            HuffmanNode("C", 6),
            HuffmanNode("D", 3),
            HuffmanNode("E", 5),
            HuffmanNode("F", 15)
    )
    tree.createHuffmanTree(nodeArray)
    tree.postOrderAsList()?.joinToString(", ")?.logE()
}