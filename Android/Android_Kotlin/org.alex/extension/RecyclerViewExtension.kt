package org.alex.extension

import android.support.v7.widget.RecyclerView
import org.alex.helper.RecyclerViewHelper
import org.alex.helper.SimpleItemDecoration
import org.alex.helper.library.LayoutType

/**
 * 作者：Alex
 * 时间：2017/10/17 22:48
 * 简述：
 *
rvFun {
layoutType = LayoutType.VLinearLayout
attachToRecyclerView(recyclerView)
}?.adapter = adapter

 */
class RecyclerViewExtension {
    var layoutType: Int = LayoutType.VLinearLayout
    var spanCount: Int = 1
    var paddingFirst: Float = 0.0F
    var paddingLast: Float = 0.0F
    var color: Int = 0xEEEEEE
    var backgroundColor: Int = 0xEEEEEE
    var dividerSize: Float = 0.5F
    var marginLeft: Float = 0.0F
    var marginRight: Float = 0.0F
    var marginV: Float = 0.0F
    fun attachToRecyclerView(view: RecyclerView?): RecyclerView? {
        RecyclerViewHelper.Builder.getInstance()
                .layoutManager(layoutType)
                .spanCount(spanCount)
                .paddingFirst(paddingFirst)
                .paddingLast(paddingLast).build().attachToRecyclerView(view)
        SimpleItemDecoration.Builder.getInstance()
                .color(color)
                .backgroundColor(backgroundColor)
                .marginH(marginLeft, marginRight)
                .marginV(marginV)
                .dividerSize(dividerSize)
                .build().attachToRecyclerView(view)
        return view
    }
}

inline fun <R> rvFun(function: RecyclerViewExtension.() -> R?): R? = RecyclerViewExtension().function()