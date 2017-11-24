package org.alex.helper;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.inputmethod.InputMethodManager;

/**
 * 作者：Alex
 * 时间：2016/12/19 22 59
 * 简述：
 */
public class KeyPadHelper {
    private static KeyPadHelper instance;
    private KeyPadHelper() {
    }

    public static KeyPadHelper getInstance() {
        if (instance == null) {
            synchronized (KeyPadHelper.class) {
                instance = (instance == null) ? new KeyPadHelper() : instance;
            }
        }
        return instance;
    }
    /**
     * 可以隐藏输入法， 自动隐藏
     */
    public boolean hiddenKeyPad(@NonNull Activity activity) {
        if (activity.getCurrentFocus() != null) {
            if (activity.getCurrentFocus().getWindowToken() != null) {
                InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                return true;
            }
        }
        return false;
    }
}
