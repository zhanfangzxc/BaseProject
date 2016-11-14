package com.yingshibao.baseproject.utils;

import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;

import javax.inject.Inject;

/**
 * Created by zhaoshanshan on 2016/11/12.
 */

public class EventPostHelper {
    private final Bus mBus;

    @Inject
    public EventPostHelper(Bus bus) {
        mBus = bus;
    }

    public Bus getBus() {
        return mBus;
    }

    public void postBusSafely(final Object event) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                mBus.post(event);
            }
        });
    }
}
