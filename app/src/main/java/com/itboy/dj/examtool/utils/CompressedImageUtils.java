package com.itboy.dj.examtool.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * 压缩图片
 *
 */
public class CompressedImageUtils {

    //质量压缩
    public static Bitmap compressImage(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) { //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//清空baos
            options -= 10;
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
        }
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        Bitmap compressBitmap = BitmapFactory.decodeStream(bais);
        return compressBitmap;
    }

   //按比例压缩:先要读取位图的尺寸与类型

 /*   BitmapFactory.Options options = new BitmapFactory.Options();
    //在解码的时候，避免内存的分配，即它会返回一个null的Bitmap对象
    //但是可以获取它的尺寸与类型,outWidth,outHeight与outMineType
    //options.inJustDecodeBounds = true;

    BitmapFactory.decodeResource(getResources(), R.drawable.picture,options);
    int imageWidth = options.outWidth;
    int imageHeight = options.outHeight;
    String imageType = options.outMimeType;*/


    //  计算inSampleSize的值
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        //原始图片的宽和高
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            while ((halfHeight / inSampleSize) > reqHeight &&
                    (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    public static Bitmap decodeSampleBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }


}
