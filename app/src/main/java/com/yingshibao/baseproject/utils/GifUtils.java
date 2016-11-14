package com.yingshibao.baseproject.utils;

import android.graphics.Bitmap;

import com.yingshibao.baseproject.widget.imageloader.GifImageDecoder;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zhaoshanshan on 2016/11/14.
 */

public class GifUtils {
    public static String toHex(int value, int length) {
        String hex = Integer.toHexString(value);
        hex = hex.toUpperCase();

        if (hex.length() < length) {
            while (hex.length() < length)
                hex = "0" + hex;
        } else if (hex.length() > length) {
            hex = hex.substring(hex.length() - length);
        }
        return hex;
    }

    public static byte[] streamToBytes(InputStream stream) throws IOException,
            OutOfMemoryError {
        byte[] buff = new byte[1024];
        int read;
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        while ((read = stream.read(buff)) != -1) {
            bao.write(buff, 0, read);
        }
        try {
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bao.toByteArray();
    }

    public static Bitmap getBitmapFromGifFile(File file, int targetWidth, int targetHeight) {

        GifImageDecoder gifDecoder = new GifImageDecoder();
        gifDecoder.setResization(targetWidth, targetHeight);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            gifDecoder.read(fis);
            return gifDecoder.getBitmap();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}