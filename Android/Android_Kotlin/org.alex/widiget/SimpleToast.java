package org.alex.widiget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import org.alex.util.BaseUtil;

/**
 * 作者：Alex
 * 时间：2016/9/6 10:06
 * 简述：
 */
@SuppressWarnings("all")
public class SimpleToast extends Toast {
    private TextView textView;

    public SimpleToast() {
        super(context());
        initView();
    }

    private void initView() {
        textView = new TextView(context());
        textView.setTextColor(Color.parseColor("#FFFFFF"));
        int dp8 = dp2px(8);
        textView.setPadding(dp8, dp8, dp8, dp8);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        textView.setLayoutParams(params);
        GradientDrawable gradientDrawableNormal = new GradientDrawable();
        gradientDrawableNormal.setShape(GradientDrawable.RECTANGLE);
        gradientDrawableNormal.setColor(Color.parseColor("#99353535"));
        float radius = dp2px(4);
        gradientDrawableNormal.setCornerRadii(new float[]{radius, radius, radius, radius, radius, radius, radius, radius});
        textView.setBackgroundDrawable(gradientDrawableNormal);
        setView(textView);
    }

    @Override
    public void setText(CharSequence text) {
        if (!TextUtils.isEmpty(text)) {
            textView.setText(text);
        } else {
            textView.setText("  ");
        }
    }

    public void setText(Object msg) {
        String text = (msg == null) ? " " : msg.toString();
        if (!TextUtils.isEmpty(text)) {
            textView.setText(text);
        } else {
            textView.setText("  ");
        }
    }

    public void shortInCenter(Object msg) {
        showShort(Gravity.CENTER, msg);
    }

    public void shortInTop(Object msg) {
        showShort(Gravity.TOP, msg);
    }

    public void shortInBottom(Object msg) {
        showShort(Gravity.BOTTOM, msg);
    }

    public void longInCenter(Object msg) {
        showLong(Gravity.CENTER, msg);
    }

    public void longInTop(Object msg) {
        showLong(Gravity.TOP, msg);
    }

    public void longInBottom(Object msg) {
        showLong(Gravity.BOTTOM, msg);
    }

    private void showShort(int gravity, Object msg) {
        show(Toast.LENGTH_SHORT, gravity, msg);
    }

    private void showLong(int gravity, Object msg) {
        show(Toast.LENGTH_LONG, gravity, msg);
    }

    private void show(int duration, int gravity, Object msg) {
        String text = (msg == null) ? " " : msg.toString();
        setText(text);
        setDuration(duration);
        if (gravity == Gravity.CENTER) {
            setGravity(gravity, 0, -100);
        } else if (gravity == Gravity.TOP) {
            setGravity(gravity, 0, 100);
        } else {
            setGravity(Gravity.BOTTOM, 0, 200);
        }
        super.show();
    }

    public static SimpleToast makeText(String text, int duration) {
        SimpleToast meiZuToast = new SimpleToast();
        meiZuToast.setText(text);
        meiZuToast.setDuration(duration);
        return meiZuToast;
    }

    /**
     * 数据转换: dp---->px
     */
    private int dp2px(float dp) {
        if (context() == null) {
            return 0;
        }
        return (int) (dp * context().getResources().getDisplayMetrics().density);
    }

    private static Context context() {
        return BaseUtil.application;
    }
}
