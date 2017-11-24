package com.alex.structure.data_structure.tree

import com.alex.structure.data_structure.datamodel.BinaryTreeNode
import java.util.*


/**
 * 作者：Alex
 * 时间：2017/10/14 09:10
 * 简述： 左节点 < 根节点 < 右节点
 */
class BinaryTree<in T : Comparable<T>> : BinTree<T> {

    private var rootNode: BinaryTreeNode<T>? = null
    /**插入节点*/
    @Suppress("VARIABLE_WITH_REDUNDANT_INITIALIZER")
    override fun insert(data: T) {
        val newNode = BinaryTreeNode<T>(data, null, null)
        if (rootNode == null) {
            rootNode = newNode
            return
        }
        var currNode = rootNode
        var parentNode = rootNode
        while (true) {
            parentNode = currNode
            if (data < currNode!!.data) {
                currNode = currNode.leftChild
                if (currNode == null) {
                    parentNode!!.leftChild = newNode
                    return
                }
            }
            if (data >= currNode.data) {
                currNode = currNode.rightChild
                if (currNode == null) {
                    parentNode!!.rightChild = newNode
                    return
                }
            }
        }
    }

    /**移除节点*/
    override fun remove(data: T): Boolean {
        var currNode = rootNode//保存当前节点
        var parent = rootNode//保存当前节点父节点
        var isLeft = true//记录是否是左几点
        //定位到将被删除的节点
        while (data != currNode!!.data) {
            if (data <= currNode.data) {
                isLeft = true//经过左节点
                if (currNode.leftChild != null) {
                    parent = currNode
                    currNode = currNode.leftChild
                }
            } else {
                isLeft = false//经过右节点
                if (currNode.rightChild != null) {
                    parent = currNode
                    currNode = currNode.rightChild
                }
            }
            if (currNode == null) {
                return false
            }
        }
        //如果被删除节点是叶子节点
        if (currNode.leftChild == null && currNode.rightChild == null) {
            if (currNode === rootNode) {
                rootNode = null
            } else if (isLeft) {
                parent!!.leftChild = null
            } else {
                parent!!.rightChild = null
            }
            //如果被删除节点只有左节点
        } else if (currNode.rightChild == null) {
            if (currNode === rootNode) {
                rootNode = currNode.leftChild
            } else if (isLeft) {
                parent!!.leftChild = currNode.leftChild
            } else {
                parent!!.rightChild = currNode.leftChild
            }
            //如果被删除节点只有右节点
        } else if (currNode.leftChild == null) {
            if (currNode === rootNode) {
                rootNode = currNode.rightChild
            } else if (isLeft) {
                parent!!.leftChild = currNode.rightChild
            } else {
                parent!!.rightChild = currNode.rightChild
            }
        } else {
            val successor = getSuccessor(currNode)
            //将后继节点与被删除的父节点进行连接，完成整个替换过程
            if (currNode === rootNode) {
                rootNode = successor
            } else if (currNode === parent!!.leftChild) {
                parent!!.leftChild = successor
            } else {
                parent!!.rightChild = successor
            }
            successor!!.leftChild = currNode.leftChild
        }
        return true

    }

    private fun <T : Comparable<T>> getSuccessor(delNode: BinaryTreeNode<T>): BinaryTreeNode<T>? {
        var curr = delNode.rightChild
        var successor = curr
        var sucParent: BinaryTreeNode<T>? = null
        while (curr != null) {
            sucParent = successor
            successor = curr
            curr = curr.leftChild
        }
        if (successor !== delNode.rightChild) {
            //将后继节点的子节点（只可能有右节点）替补到后继节点的位置上
            sucParent!!.leftChild = successor!!.rightChild
            //将被删除的右孩子连接到后继节点的右节点上
            successor.rightChild = delNode.rightChild
            //将被删除的左孩子连接到后继节点的右节点上（就是用后继节点替换掉被删除的节点）
        }
        return successor
    }

    /**先序遍历：根左右*/
    override fun preOrderAsList(): List<Comparable<T>>? {
        val dataList = ArrayList<T>()
        visitEveryNode(PRE_ORDER_VISIT, dataList, rootNode)
        return dataList
    }


    /**中序遍历*/
    override fun inOrderAsList(): List<Comparable<T>>? {
        val dataList = ArrayList<T>()
        visitEveryNode(IN_ORDER_VISIT, dataList, rootNode)
        return dataList
    }

    /**后序遍历*/
    override fun postOrderAsList(): List<Comparable<T>>? {
        val dataList = ArrayList<T>()
        visitEveryNode(POST_ORDER_VISIT, dataList, rootNode)
        return dataList
    }


    private fun <T : Comparable<T>> visitEveryNode(visit: Int, list: ArrayList<T>, node: BinaryTreeNode<T>?) {
        if (node != null && visit == PRE_ORDER_VISIT) {
            list.add(node.data)
            visitEveryNode(visit, list, node.leftChild)
            visitEveryNode(visit, list, node.rightChild)
        }
        if (node != null && visit == IN_ORDER_VISIT) {
            visitEveryNode(visit, list, node.leftChild)
            list.add(node.data)
            visitEveryNode(visit, list, node.rightChild)
        }
        if (node != null && visit == POST_ORDER_VISIT) {
            visitEveryNode(visit, list, node.leftChild)
            visitEveryNode(visit, list, node.rightChild)
            list.add(node.data)
        }
    }

    companion object {
        private const val PRE_ORDER_VISIT = 0
        private const val IN_ORDER_VISIT = 1
        private const val POST_ORDER_VISIT = 2
    }
}


