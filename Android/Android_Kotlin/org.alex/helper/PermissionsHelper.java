package org.alex.helper;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import org.alex.util.BaseUtil;

/**
 * 作者：Alex
 * 时间：2017/1/18 9:59
 * 简述：
 */
public class PermissionsHelper {
    private static Context context() {
        return BaseUtil.getInstance().application();
    }

    /**
     * 检测 是否 拥有 这些权限
     *
     * @param permissions
     * @return
     */
    public boolean checkSelfPermission(String... permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context(), permission) == PackageManager.PERMISSION_DENIED) {
                return true;
            }
        }
        return false;
    }

    /***
     * 用户关闭并不再提醒所有权限提示
     *
     * @param activity
     * @param permissions
     */
    public boolean hasDelayPermissions(Activity activity, String... permissions) {
        int count = 0;
        for (String permission : permissions) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, permission) && ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_DENIED) {
                count++;
            }
        }
        if (count == permissions.length) {
            return true;
        }
        return false;
    }

    /***
     * 申请权限
     *
     * @param activity
     * @param permissions
     */
    public void requestPermissions(Activity activity, int requestCode, String... permissions) {
        ActivityCompat.requestPermissions(activity, permissions, requestCode);
    }

    private static PermissionsHelper instance;

    private PermissionsHelper() {
    }

    public static PermissionsHelper getInstance() {
        if (instance == null) {
            synchronized (PermissionsHelper.class) {
                instance = (instance == null) ? new PermissionsHelper() : instance;
            }
        }
        return instance;
    }


}
