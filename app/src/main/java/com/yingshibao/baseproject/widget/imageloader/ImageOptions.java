package com.yingshibao.baseproject.widget.imageloader;

/**
 * Created by zhaoshanshan on 2016/11/14.
 */

public class ImageOptions {
    private int width;
    private int height;

    public ImageOptions(Builder builder) {
        this.width = builder.width;
        this.height = builder.height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public static class Builder {
        private int width;
        private int height;

        public Builder() {
            this.width = 0;
            this.height = 0;
        }

        public Builder width(int width) {
            this.width = width;
            return this;
        }

        public Builder height(int height) {
            this.height = height;
            return this;
        }
    }
}
