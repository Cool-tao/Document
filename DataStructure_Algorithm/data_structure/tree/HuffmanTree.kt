package com.alex.structure.data_structure.tree

import com.alex.structure.data_structure.datamodel.HuffmanNode
import org.alex.extension.logW
import org.alex.util.LogTrack
import org.alex.util.logtrack.PrintTerminal
import java.util.ArrayList

/**
 * 作者：Alex
 * 时间：2017/10/1915:25
 * 简述：
 */
class HuffmanTree :HuffTree{
    private var rootNode: HuffmanNode? = null
    fun createHuffmanTree(array: Array<HuffmanNode>) {
        quickSort(array, 0, array.size - 1)
        var position = 0
        while (position < array.size - 1) {
            val leftChildNode = array[position]
            val rightChildNode =  array[position + 1]
            val parentNode = HuffmanNode("", rightChildNode.weight + leftChildNode.weight, leftChildNode, rightChildNode)
            array[++position] = parentNode
            quickSort(array, position, array.size - 1)
            if (position >= array.size - 1) {
                rootNode = parentNode
            }
        }
    }

    /** 小于等于基准数的，在左边子集； 大于 基准数的， 在右边子集 */
    @Suppress("MemberVisibilityCanPrivate")
    private fun quickSort(array: Array<HuffmanNode>, left: Int, right: Int) {
        if (left >= right || left < 0 || left >= array.size || right >= array.size) {
            return
        }
        var i = left
        var j = right
        val pivot = array[left]
        while (i < j) {
            while (i < j && array[j].weight > pivot.weight) {
                j--
            }
            array[i] = array[j]
            while (i < j && array[i].weight <= pivot.weight) {
                i++
            }
            array[j] = array[i]
        }
        array[i] = pivot
        //LogTrack.w(array.joinToString(", "))
        quickSort(array, left, i - 1)
        //LogTrack.w(array.joinToString(", "))
        quickSort(array, i + 1, right)
        //LogTrack.w(array.joinToString(", "))
    }

    /**先序遍历：根左右*/
    fun preOrderAsList(): List<HuffmanNode>? {
        val dataList = ArrayList<HuffmanNode>()
        visitEveryNode(PRE_ORDER_VISIT, dataList, rootNode)
        return dataList
    }


    /**中序遍历*/
    fun inOrderAsList(): List<HuffmanNode>? {
        val dataList = ArrayList<HuffmanNode>()
        visitEveryNode(IN_ORDER_VISIT, dataList, rootNode)
        return dataList
    }

    /**后序遍历*/
    fun postOrderAsList(): List<HuffmanNode>? {
        val dataList = ArrayList<HuffmanNode>()
        visitEveryNode(POST_ORDER_VISIT, dataList, rootNode)
        return dataList
    }


    private fun  visitEveryNode(visit: Int, list: ArrayList<HuffmanNode>, node: HuffmanNode?) {
        if (node != null && visit == PRE_ORDER_VISIT) {
            list.add(node)
            visitEveryNode(visit, list, node.leftChild)
            visitEveryNode(visit, list, node.rightChild)
        }
        if (node != null && visit == IN_ORDER_VISIT) {
            visitEveryNode(visit, list, node.leftChild)
            list.add(node)
            visitEveryNode(visit, list, node.rightChild)
        }
        if (node != null && visit == POST_ORDER_VISIT) {
            visitEveryNode(visit, list, node.leftChild)
            visitEveryNode(visit, list, node.rightChild)
            list.add(node)
        }
    }

    companion object {
        private const val PRE_ORDER_VISIT = 0
        private const val IN_ORDER_VISIT = 1
        private const val POST_ORDER_VISIT = 2
    }

}