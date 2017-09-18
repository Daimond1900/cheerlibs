package com.wl.daydayup.mcheerlibs.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * <b>
 * 检查网络 -- 需要加权限 --> android.permission.ACCESS_NETWORK_STATE
 * 其中包括 1.是否有网络 2.是否是wifi连接 3.打开网络设置
 * </b>
 * Created by 1900 on 2017/9/13.
 */

public class NetUtils {
    private NetUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 判断网络是否连接
     *
     * @return true false
     */
    public static boolean isConnected(Context context) {

        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (null != connectivity) {

            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (null != info && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断是否是wifi连接
     */
    public static boolean isWifi(Context context) {
        if (isConnected(context)) {
            ConnectivityManager cm = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            if (cm == null) {
                return false;
            } else {
                return cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;
            }
        }
        return false;
    }

    /**
     * 打开网络设置界面
     */
    public static void openSetting(final Activity activity) {
        Intent intent = null;
        //判断手机系统的版本  即API大于10 就是3.0或以上版本
        if (android.os.Build.VERSION.SDK_INT > 10) {
            intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
        } else {
            intent = new Intent();
            ComponentName component = new ComponentName("com.android.settings", "com.android.settings.WirelessSettings");
            intent.setComponent(component);
            intent.setAction("android.intent.action.VIEW");
        }
        activity.startActivity(intent);
    }
}
