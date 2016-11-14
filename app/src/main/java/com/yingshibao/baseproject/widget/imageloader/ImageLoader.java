package com.yingshibao.baseproject.widget.imageloader;

import android.widget.ImageView;

import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.yingshibao.baseproject.R;
import com.yingshibao.baseproject.view.FrescoImageView;

/**
 * Created by zhaoshanshan on 2016/11/14.
 */

public class ImageLoader {
    private int type;
    private String url;
    private int placeHolder;
    private ImageView mImageView;
    private int wifiStrategy;
    private FrescoImageView mFrescoImageView;
    private BasePostprocessor mBasePostprocessor;
    private BaseControllerListener mBaseControllerListener;
    private ImageOptions mImageOptions;

    private ImageLoader(Builder builder) {
        this.type = builder.type;
        this.url = builder.url;
        this.placeHolder = builder.placeHolder;
        this.mImageView = builder.mImageView;
        this.wifiStrategy = builder.wifiStrategy;
        this.mFrescoImageView = builder.mFrescoImageView;
    }

    public int getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public int getPlaceHolder() {
        return placeHolder;
    }

    public ImageView getImgView() {
        return mImageView;
    }

    public FrescoImageView getFrescoImageview() {
        return mFrescoImageView;
    }

    public int getWifiStrategy() {
        return wifiStrategy;
    }

    public ImageOptions getImageOptions() {
        return mImageOptions;
    }

    public BasePostprocessor getBasePostprocessor() {
        return mBasePostprocessor;
    }

    public BaseControllerListener getBaseControllerListener() {
        return mBaseControllerListener;
    }

    public static class Builder {
        private int type;
        private String url;
        private int placeHolder;
        private ImageView mImageView;
        private FrescoImageView mFrescoImageView;
        private int wifiStrategy;
        private BasePostprocessor mBasePostprocessor;
        private BaseControllerListener mBaseControllerListener;
        private ImageOptions mImageOptions;

        public Builder() {
            this.type = ImageLoaderUtil.PIC_SMALL;
            this.url = "";
            this.placeHolder = R.mipmap.ic_launcher;
            this.mImageView = null;
            this.wifiStrategy = ImageLoaderUtil.LOAD_STRATEGY_NORMAL;
            this.mFrescoImageView = null;
            this.mBaseControllerListener = null;
            this.mBasePostprocessor = null;
            this.mImageOptions = null;
        }

        public Builder type(int type) {
            this.type = type;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder placeHolder(int placeHolder) {
            this.placeHolder = placeHolder;
            return this;
        }

        public Builder imgView(ImageView imgView) {
            this.mImageView = imgView;
            return this;
        }

        public Builder frescoImageview(FrescoImageView imgView) {
            this.mFrescoImageView = imgView;
            return this;
        }

        public Builder strategy(int strategy) {
            this.wifiStrategy = strategy;
            return this;
        }

        public Builder basePostProcessor(BasePostprocessor basePostprocessor) {
            this.mBasePostprocessor = basePostprocessor;
            return this;
        }

        public Builder baseControllerListener(BaseControllerListener controllerListener) {
            this.mBaseControllerListener = controllerListener;
            return this;
        }

        public Builder imageOptions(ImageOptions imageOptions) {
            this.mImageOptions = imageOptions;
            return this;
        }

        public ImageLoader build() {
            return new ImageLoader(this);
        }
    }
}
