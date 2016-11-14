package com.yingshibao.baseproject.widget.imageloader;

import android.content.Context;
import android.net.Uri;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

/**
 * Created by zhaoshanshan on 2016/11/14.
 */

public class FrescoImageLoaderStrategy implements BaseImageLoaderStrategy {
    @Override
    public void loadImage(Context context, ImageLoader imageLoader) {

    }

    public static void loadNormal(Context context, ImageLoader imageLoader) {
        ResizeOptions resizeOptions = null;
        ImageOptions imageOptions = imageLoader.getImageOptions();
        if (imageOptions.getWidth() > 0 && imageOptions.getHeight() > 0) {
            resizeOptions = new ResizeOptions(imageOptions.getWidth(), imageOptions.getHeight());
        }
        ImageRequest request =
                ImageRequestBuilder.newBuilderWithSource(Uri.parse(imageLoader.getUrl()))
                        .setPostprocessor(imageLoader.getBasePostprocessor())
                        .setResizeOptions(resizeOptions)
                        .setProgressiveRenderingEnabled(true)//支持图片渐进式加载
                        .setAutoRotateEnabled(true)//支持图片自动旋转
                        .build();
        PipelineDraweeController controller =
                (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                        .setImageRequest(request)
                        .setControllerListener(imageLoader.getBaseControllerListener())
                        .setOldController(imageLoader.getFrescoImageview().getController())
                        .setAutoPlayAnimations(true)
                        .build();

        imageLoader.getFrescoImageview().setController(controller);
    }

    public static void loadCache(Context context, ImageLoader imageLoader) {

    }
}
