package com.yingshibao.baseproject.utils;

import android.content.Context;
import android.widget.Toast;

import javax.inject.Inject;

/**
 * Created by zhaoshanshan on 2016/11/12.
 * <p>
 * Toast工具类
 */

public class ToastUtils {
    private static Context mContext;
    private static ToastUtils mInstance;
    private Toast mToast;

    @Inject
    private ToastUtils( Context context) {
        mContext = context;
    }

    public void showToast(String text, int duration) {
        if (mToast == null) {
            mToast = Toast.makeText(mContext, text, duration);
        } else {
            mToast.setText(text);
            mToast.setDuration(duration);
        }
        mToast.show();
    }

    public void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }
}
