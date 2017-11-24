package org.alex.helper;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;

/**
 * 作者：Alex
 * 时间：2017/1/18 19:13
 * 简述：
 */
public class StatusBarHelper {
    private static StatusBarHelper instance;

    private StatusBarHelper() {
    }

    public static StatusBarHelper getInstance() {
        if (instance == null) {
            synchronized (StatusBarHelper.class) {
                instance = (instance == null) ? new StatusBarHelper() : instance;
            }
        }
        return instance;
    }

    /**
     * #ff33b5e5
     * @param activity
     */
    public void setStatusBarColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ViewGroup firstChildAtDecorView = ((ViewGroup) ((ViewGroup) activity.getWindow().getDecorView()).getChildAt(0));
            View statusView = new View(activity);
            ViewGroup.LayoutParams statusViewLp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(activity));
            //颜色的设置可抽取出来让子类实现之
            statusView.setBackgroundColor(color);
            firstChildAtDecorView.addView(statusView, 0, statusViewLp);
        }
    }

    private int getStatusBarHeight(Context context) {
        int resId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            return context.getResources().getDimensionPixelSize(resId);
        }
        return 0;
    }


}
