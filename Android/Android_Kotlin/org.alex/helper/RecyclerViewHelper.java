package org.alex.helper;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import org.alex.helper.library.LayoutType;

/**
 * 作者：Alex
 * 时间：2016年12月12日
 * 简述：
 */
@SuppressWarnings("all")
public class RecyclerViewHelper {
    protected Builder builder;

    private RecyclerViewHelper(Builder builder) {
        this.builder = builder;
    }

    /**
     * recyclerView  关联 布局类型
     */
    public void attachToRecyclerView(RecyclerView recyclerView) {
        layoutManager(recyclerView, builder.layoutType, builder.spanCount);
    }

    private void layoutManager(RecyclerView recyclerView, @LayoutType int layoutType, int spanCount) {
        if (LayoutType.HLinearLayout == layoutType) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());
            layoutManager.setOrientation(OrientationHelper.HORIZONTAL);
            recyclerView.setLayoutManager(layoutManager);
        } else if (LayoutType.VLinearLayout == layoutType) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());
            layoutManager.setOrientation(OrientationHelper.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
        } else if (LayoutType.GridLayout == layoutType) {
            GridLayoutManager layoutManager = new GridLayoutManager(recyclerView.getContext(), spanCount);
            layoutManager.setSpanCount(spanCount);
            recyclerView.setLayoutManager(layoutManager);
        } else if (LayoutType.HStaggeredGridLayout == layoutType) {
            StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(spanCount, OrientationHelper.HORIZONTAL);
            layoutManager.setSpanCount(spanCount);
            recyclerView.setLayoutManager(layoutManager);
        } else if (LayoutType.VStaggeredGridLayout == layoutType) {
            StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(spanCount, OrientationHelper.VERTICAL);
            layoutManager.setSpanCount(spanCount);
            recyclerView.setLayoutManager(layoutManager);
        }
        int paddingFirst = dp2px(recyclerView, builder.paddingFirst);
        int paddingLast = dp2px(recyclerView, builder.paddingLast);
        if (LayoutType.HLinearLayout == builder.layoutType || LayoutType.HStaggeredGridLayout == builder.layoutType) {
            recyclerView.setPadding(paddingFirst, 0, paddingLast, 0);
        } else {
            recyclerView.setPadding(0, paddingFirst, 0, paddingLast);
        }
        recyclerView.setClipToPadding(false);
    }

    /**
     * 数据转换: dp---->px
     */
    private int dp2px(View view, float dp) {
        return (int) (dp * view.getResources().getDisplayMetrics().density + 0.5F);
    }

    public static final class Builder {
        /**
         * 给 第一行 数据 添加 padding
         * 给 最后一行 数据 添加 padding
         */
        protected float paddingFirst, paddingLast;
        protected int layoutType, spanCount;
        private Builder() {
        }

        public static Builder getInstance() {
            return new Builder();
        }

        public Builder paddingFirst(float padding) {
            paddingFirst = padding;
            return this;
        }

        public Builder paddingLast(float padding) {
            paddingLast = padding;
            return this;
        }

        public Builder layoutManager(@LayoutType int layoutType) {
            this.layoutType = layoutType;
            return this;
        }

        public Builder spanCount(int spanCount) {
            this.spanCount = spanCount;
            return this;
        }

        public RecyclerViewHelper build() {
            spanCount = (spanCount <= 0) ? 1 : spanCount;
            return new RecyclerViewHelper(this);
        }

    }


    public static String layoutManager2String(RecyclerView.LayoutManager layoutManager) {
        String result = "线性的 - 垂直";
        if (layoutManager instanceof GridLayoutManager) {
            result = "网格的 " + ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
            result = OrientationHelper.HORIZONTAL == staggeredGridLayoutManager.getOrientation() ? "瀑布流的 - 水平 " : "瀑布流的 - 垂直 ";
            result += staggeredGridLayoutManager.getSpanCount();
        } else {
            result = (OrientationHelper.VERTICAL == ((LinearLayoutManager) layoutManager).getOrientation()) ? "线性的 - 垂直" : "线性的 - 水平";
        }
        return result;
    }

}
