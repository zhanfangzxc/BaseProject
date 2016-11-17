package com.yingshibao.baseproject.util;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

public final class ViewUtil {

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm =
                (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
    }

}
