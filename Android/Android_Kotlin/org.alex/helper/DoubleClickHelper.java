package org.alex.helper;

/**
 * 作者：Alex
 * 时间：2016/12/22 22 58
 * 简述：
 * ＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊
 * //  双击 退出 怎么使 ？
 * <p>
 * public void onBackPressed() {
 * * * if (DoubleClickHelper.getInstance().notOnBackPressed()) {
 * * ** * ToastUtil.shortNormal("再点一次返回");
 * * *} else {
 * * * super.onBackPressed();
 * * *}
 * }
 * <p>
 * <p>
 * // 频繁 点击 怎么 屏蔽？
 * public void onClick(View v) {
 * * * if (DoubleClickHelper.getInstance().isClickFrequently()) {
 * * * * * return;
 * * * }
 * }
 * ＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊
 */
@SuppressWarnings("all")
public class DoubleClickHelper {
    private static DoubleClickHelper instance;
    /**
     * 上次点击的时间。
     */
    private static long lastDoubleClickTime;
    /**
     * 两次点击的 时间间隔
     */
    private static int doubleClickDelayTime;
    /**
     * 上次点击的时间
     */
    private static long lastClickTime;
    /**
     * 最小 点击 间隔 时间
     */
    private static long minClickDuration;

    private DoubleClickHelper() {
        minClickDuration = 300L;
        doubleClickDelayTime = 1500;
    }

    public static DoubleClickHelper getInstance() {
        if (instance == null) {
            synchronized (DoubleClickHelper.class) {
                instance = (instance == null) ? new DoubleClickHelper() : instance;
            }
        }
        return instance;
    }

    /**
     * 判断点击过快 时间间隔 为 300毫秒
     */
    public boolean isClickFrequently() {
        long currClickTime = System.currentTimeMillis();
        if ((currClickTime - lastClickTime) < minClickDuration) {
            return true;
        }
        lastClickTime = currClickTime;
        return false;
    }

    /**
     * 如果 两次 点击的时间差 > doubleClickDelayTime, 返回  true（间隔的时间较长， 不在1500ms内）；
     * 否则 返回 false（间隔的时间较短，在1500ms内）
     */
    public boolean notOnBackPressed() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - lastDoubleClickTime <= doubleClickDelayTime) {
            lastDoubleClickTime = -1;
            return false;
        }
        lastDoubleClickTime = currentTimeMillis;
        return true;
    }

    /**
     * 再点击一次的 间隔时间
     *
     * @param delayTime 单位 ms
     */
    public DoubleClickHelper doubleClickDelayTime(int delayTime) {
        doubleClickDelayTime = delayTime;
        return this;
    }

    /**
     * 小于  delayTime 毫秒，算 频繁点击
     *
     * @param delayTime 单位 ms
     */
    public DoubleClickHelper minClickDuration(int delayTime) {
        minClickDuration = delayTime;
        return this;
    }

}
