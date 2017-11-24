package org.alex.helper;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.view.ViewCompat;
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
public class SimpleItemDecoration extends RecyclerView.ItemDecoration {
    private GradientDrawable dividerDrawable;
    private Builder builder;
    private int layoutType;
    private int itemCount;
    private int spanCount;
    private boolean isPaddinged;

    private SimpleItemDecoration(Builder builder) {
        this.builder = builder;
        initView(builder);
    }

    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(canvas, parent, state);
        if (layoutType == LayoutType.VLinearLayout) {/*线性的 - 垂直滑动的子布局， 画 水平线*/
            drawHorizontalLine(canvas, parent, state);
        } else if (layoutType == LayoutType.HLinearLayout) {/*线性的 - 水平滑动的子布局， 画垂直线*/
            drawVerticalLine(canvas, parent, state);
        }
    }

    private void initView(Builder builder) {
        dividerDrawable = new GradientDrawable();
        dividerDrawable.setColor(builder.color);
        layoutType = -1;
        itemCount = -1;
        spanCount = -1;
        isPaddinged = false;
    }

    /**
     * 这个方法比onDraw()先执行
     * 这里的作用是设置Rect的范围，
     * RecyclerView会调用这个方法来设置每个item view的padding值
     * Rect对应item view：
     * left=paddingLeft
     * right=paddingLeft
     * top=paddingTop
     * bottom=paddingBottom
     **/
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //final int childCount = parent.getChildCount();
        if (layoutType == -1) {
            RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {
                spanCount = (spanCount < 0) ? ((GridLayoutManager) layoutManager).getSpanCount() : spanCount;
                layoutType = spanCount > 1 ? LayoutType.GridLayout : LayoutType.VLinearLayout;
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
                layoutType = OrientationHelper.HORIZONTAL == staggeredGridLayoutManager.getOrientation() ? LayoutType.HStaggeredGridLayout : LayoutType.VStaggeredGridLayout;
                spanCount = (spanCount < 0) ? staggeredGridLayoutManager.getSpanCount() : spanCount;
            } else {
                layoutType = (OrientationHelper.VERTICAL == ((LinearLayoutManager) layoutManager).getOrientation()) ? LayoutType.VLinearLayout : LayoutType.HLinearLayout;
                spanCount = 1;
            }
        }

        RecyclerView.Adapter adapter = parent.getAdapter();
        itemCount = adapter.getItemCount();

        int position = parent.getChildAdapterPosition(view);
        int paddFirst = 0, paddingLast = 0;

        if (LayoutType.VLinearLayout == layoutType) {
            int paddingBottom = dp2px(view, builder.dividerSize);
            outRect.set(0, paddFirst, 0, paddingBottom);
        } else if (LayoutType.HLinearLayout == layoutType) {
            int paddingRight = dp2px(view, builder.dividerSize);
            outRect.set(paddFirst, 0, paddingRight, 0);
        } else if (LayoutType.GridLayout == layoutType) {
            int paddingV = dp2px(view, builder.dividerSize);
            int paddingH = dp2px(view, builder.dividerSize);
            if (spanCount == 2 && isFirstInRows(position)) {
                outRect.set(0, paddFirst, paddingH / 2, paddingV);
            } else if (spanCount == 2 && isLastInRows(position)) {
                outRect.set(paddingH / 2, paddFirst, 0, paddingV);
            } else if (spanCount == 3 && isFirstInRows(position)) {
                outRect.set(0, paddFirst, paddingH / 2, paddingV);
            } else if (spanCount == 3 && isMiddleInRows(position)) {
                outRect.set(paddingH / 4, paddFirst, paddingH / 4, paddingV);
            } else if (spanCount == 3 && isLastInRows(position)) {
                outRect.set(paddingH / 2, paddFirst, 0, paddingV);
            } else if (spanCount > 3 && isFirstInRows(position)) {
                outRect.set(paddingH / 2, paddFirst, paddingH / 2, paddingV);
            } else if (spanCount > 3 && isMiddleInRows(position)) {
                outRect.set(paddingH / 2, paddFirst, paddingH / 2, paddingV);
            } else if (spanCount > 3 && isLastInRows(position)) {
                outRect.set(paddingH / 2, paddFirst, paddingH / 2, paddingV);
            }
            if (spanCount > 3 && !isPaddinged) {
                isPaddinged = true;
                parent.setPadding(paddingH / 2, parent.getPaddingTop(), paddingH / 2, parent.getPaddingBottom());
                parent.setClipToPadding(false);
            }
        }
    }

    private void drawHorizontalLine(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int top = child.getBottom() + params.bottomMargin + Math.round(ViewCompat.getTranslationY(child));
            int bottom = top + dp2px(parent, builder.dividerSize);
            if (LayoutType.VLinearLayout == layoutType || LayoutType.HLinearLayout == layoutType) {
                dividerDrawable.setBounds(left, top, right, bottom);
                dividerDrawable.setColor(builder.backgroundColor);
                dividerDrawable.draw(canvas);
                int position = parent.getChildAdapterPosition(child);
                if (isLastRows(position)) {
                    continue;
                }
                int marginLeft = dp2px(parent, builder.marginLeft);
                int marginRight = dp2px(parent, builder.marginRight);
                left = left + marginLeft;
                right = right - marginRight;
                dividerDrawable.setBounds(left, top, right, bottom);
                dividerDrawable.setColor(builder.color);
                dividerDrawable.draw(canvas);
            } else if (LayoutType.GridLayout == layoutType) {/*网格 布局*/
                spanCount = spanCount <= 0 ? getSpanCount(parent) : spanCount;
            }
        }
    }

    private void drawVerticalLine(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getRight() + params.rightMargin + Math.round(ViewCompat.getTranslationX(child));
            int top = parent.getPaddingTop();
            int right = left + dp2px(parent, builder.dividerSize);
            int bottom = parent.getHeight() - parent.getPaddingBottom();
            if (LayoutType.VLinearLayout == layoutType || LayoutType.HLinearLayout == layoutType) {
                int position = parent.getChildAdapterPosition(child);
                if (isLastRows(position)) {
                    continue;
                }
                dividerDrawable.setBounds(left, top, right, bottom);
                dividerDrawable.setColor(builder.backgroundColor);
                dividerDrawable.draw(canvas);
                int marginV = dp2px(parent, builder.marginV);
                top += marginV;
                bottom -= marginV;
                dividerDrawable.setBounds(left, top, right, bottom);
                dividerDrawable.setColor(builder.color);
                dividerDrawable.draw(canvas);

            } else if (LayoutType.GridLayout == layoutType) {/*网格 布局*/

            }

        }
    }

    /**
     * 获取 网格多少列
     */
    private int getSpanCount(RecyclerView parent) {
        if (parent.getLayoutManager() instanceof GridLayoutManager) {
            GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
            return layoutManager.getSpanCount();
        }
        return 1;
    }

    /**
     * 获取 行数
     *
     * @return
     */
    private int getRowsCount() {
        if (spanCount == 1) {
            return itemCount;
        }
        int offset = itemCount % spanCount;
        int rowsCount = itemCount / spanCount;
        return offset == 0 ? rowsCount : rowsCount + 1;
    }

    /**
     * 获取 position  在第几排， 下标 从 0 开始
     *
     * @param position
     * @return
     */
    private int getRowsIndex(int position) {
        return position / spanCount;
    }

    /**
     * 在 最后 一排
     */
    private boolean isFirstRows(int position) {
        return getRowsIndex(position) == 0;
    }

    /**
     * 在 最后 一排
     */
    private boolean isLastRows(int position) {
        return getRowsIndex(position) == getRowsCount() - 1;
    }


    /**
     * 是当前行 的 第一个
     */
    public boolean isFirstInRows(int position) {
        if (spanCount <= 1) {
            return true;
        }
        return position % spanCount == 0;
    }

    /**
     * 是当前行 的 最后一个
     */
    private boolean isLastInRows(int position) {
        if (spanCount <= 1) {
            return true;
        }
        return position % spanCount == (spanCount - 1);
    }

    /**
     * 是当前行 的 中间一个
     */
    private boolean isMiddleInRows(int position) {
        if (spanCount <= 1) {
            return true;
        }
        if (spanCount == 2) {
            return false;
        }
        int offset = position % spanCount;
        return (offset > 0) & (offset < spanCount - 1);
    }

    /**
     * RecyclerView 关联 分割线类型
     *
     * @param recyclerView
     */
    public void attachToRecyclerView(RecyclerView recyclerView) {
        recyclerView.addItemDecoration(this);
    }

    public static class Builder {
        protected int color;
        protected int backgroundColor;
        protected float dividerSize;
        protected float marginLeft, marginRight, marginV;

        private Builder() {
        }

        public static Builder getInstance() {
            return new Builder();
        }

        public Builder backgroundColor(int color) {
            this.backgroundColor = color;
            return this;
        }

        public Builder backgroundColor(String color) {
            return backgroundColor(Color.parseColor(color));
        }

        public Builder color(int color) {
            this.color = color;
            return this;
        }

        public Builder color(String color) {
            return color(Color.parseColor(color));
        }

        /**
         * 单位 dp
         */
        public Builder dividerSize(float size) {
            this.dividerSize = size;
            return this;
        }

        /**
         * 单位 dp，
         * <br/>
         * this.marginLeft = margin[0];
         * <br/>
         * this.marginRight = (margin.length == 1) ? margin[0] : margin[1];
         */
        public Builder marginH(float... margin) {
            if (margin == null) {
                this.marginLeft = 0;
                this.marginRight = 0;
            }
            this.marginLeft = margin[0];
            this.marginRight = (margin.length == 1) ? margin[0] : margin[1];
            return this;
        }

        /**
         * 单位 dp
         */
        public Builder marginV(float marginV) {
            this.marginV = marginV;
            return this;
        }

        public SimpleItemDecoration build() {
            return new SimpleItemDecoration(this);
        }
    }

    /**
     * 数据转换: dp---->px
     */
    private int dp2px(View view, float dp) {
        if (view == null) {
            return -1;
        }
        return (int) (dp * view.getResources().getDisplayMetrics().density + 0.5F);
    }

}
