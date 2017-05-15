package com.lewis.codingrode.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.Hashtable;

/**
 * 生成二维码的工具类
 * 具体参考：
 * 1、http://blog.csdn.net/dgs960825/article/details/51200863
 * 2、https://github.com/vivian8725118/ZXingGenerator
 * Created by lewis on 2017/5/15.
 */

public class QRCodeUtil {

    private static final int SIZE = 500; //二维码的宽
    /**
     * 黑点颜色
     */
    private static final int BLACK = 0xFF000000;
    /**
     * 白色
     */
    private static final int WHITE = 0xFFFFFFFF;
    /**
     * LOGO宽度值,最大不能大于二维码20%宽度值,大于可能会导致二维码信息失效
     */
    private static final int LOGO_WIDTH_MAX = SIZE / 5;
    /**
     * LOGO宽度值,最小不能小于二维码10%宽度值,小于影响Logo与二维码的整体搭配
     */
    private static final int LOGO_WIDTH_MIN = SIZE / 10;

    /**
     * 生成二维码，默认大小为500*500
     *
     * @param content 需要生成二维码的文字、网址等
     * @return
     */
    public static Bitmap createQRCode(String content) throws WriterException {
        return createQRCode(content, SIZE);
    }

    /**
     * 生成二维码
     *
     * @param content 文字或网址
     * @param size    二维码的大小
     * @return
     */
    public static Bitmap createQRCode(String content, int size) throws WriterException {
        Hashtable<EncodeHintType, String> hints = new Hashtable<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        //图像数据转换，使用了矩阵转换
        BitMatrix mBitMatrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, size, size, hints);
        int pixels[] = new int[size * size];
        //下面这里按照二维码的算法，逐个生成二维码的图片，
        //两个for循环是图片横列扫描的结果
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (mBitMatrix.get(x, y)) {
                    pixels[y * size + x] = 0xff000000;
                } else {
                    pixels[y * size + x] = 0xffffffff;
                }
            }
        }
        //生成二维码图片的格式，使用ARGB_8888
        Bitmap qrCodeBitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        qrCodeBitmap.setPixels(pixels, 0, size, 0, 0, size, size);
        return qrCodeBitmap;
    }

    /**
     * 生成带logo的二维码
     *
     * @param content
     * @param logoBitmap
     * @return
     * @throws WriterException
     */
    public static Bitmap createQRCode(String content, Bitmap logoBitmap) throws WriterException {
        return createQRCode(content, SIZE, logoBitmap);
    }

    /**
     * 生成带logo的二维码
     *
     * @param content
     * @param size
     * @param logoBitmap
     * @return
     * @throws WriterException
     */
    public static Bitmap createQRCode(String content, int size, Bitmap logoBitmap) throws WriterException {
        int logoWidth = logoBitmap.getWidth();
        int logoHeight = logoBitmap.getHeight();
        int logoHaleWidth = logoWidth >= SIZE ? LOGO_WIDTH_MIN
                : LOGO_WIDTH_MAX;
        int logoHaleHeight = logoHeight >= SIZE ? LOGO_WIDTH_MIN
                : LOGO_WIDTH_MAX;
        // 将logo图片按martix设置的信息缩放
        Matrix matrix = new Matrix();
        float sx = (float) logoHaleWidth / logoWidth;
        float sy = (float) logoHaleHeight / logoHeight;
        matrix.setScale(sx, sy);// 设置缩放信息
        Bitmap newLogoBitmap = Bitmap.createBitmap(logoBitmap, 0, 0, logoWidth,
                logoHeight, matrix, false);
        int newLogoWidth = newLogoBitmap.getWidth();
        int newLogoHeight = newLogoBitmap.getHeight();

        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); //容错级别
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.MAX_SIZE, LOGO_WIDTH_MAX);// 设置图片的最大值
        hints.put(EncodeHintType.MIN_SIZE, LOGO_WIDTH_MIN);// 设置图片的最小值
        //设置空白边距的宽度
//            hints.put(EncodeHintType.MARGIN, 2); //default is 4
        // 生成二维矩阵,编码时指定大小,不要生成了图片以后再进行缩放,这样会模糊导致识别失败
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
                BarcodeFormat.QR_CODE, SIZE, SIZE, hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        int halfW = width / 2;
        int halfH = height / 2;

        int[] pixels = new int[size * size];
        // 下面这里按照二维码的算法，逐个生成二维码的图片，
        // 两个for循环是图片横列扫描的结果
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                /*
                 * 取值范围,可以画图理解下
                 * halfW + newLogoWidth / 2 - (halfW - newLogoWidth / 2) = newLogoWidth
                 * halfH + newLogoHeight / 2 - (halfH - newLogoHeight) = newLogoHeight
                 */
                if (x > halfW - newLogoWidth / 2 && x < halfW + newLogoWidth / 2
                        && y > halfH - newLogoHeight / 2 && y < halfH + newLogoHeight / 2) {// 该位置用于存放图片信息
                    /*
                     *  记录图片每个像素信息
                     *  halfW - newLogoWidth / 2 < x < halfW + newLogoWidth / 2
                     *  --> 0 < x - halfW + newLogoWidth / 2 < newLogoWidth
                     *   halfH - newLogoHeight / 2  < y < halfH + newLogoHeight / 2
                     *   -->0 < y - halfH + newLogoHeight / 2 < newLogoHeight
                     *   刚好取值newLogoBitmap。getPixel(0-newLogoWidth,0-newLogoHeight);
                     */
                    pixels[y * width + x] = newLogoBitmap.getPixel(
                            x - halfW + newLogoWidth / 2, y - halfH + newLogoHeight / 2);
                } else {
                    pixels[y * width + x] = bitMatrix.get(x, y) ? BLACK : WHITE;// 设置信息
                }
            }
        }
        //生成二维码图片的格式，使用ARGB_8888
        Bitmap qrCodeBitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        // 通过像素数组生成bitmap,具体参考api
        qrCodeBitmap.setPixels(pixels, 0, size, 0, 0, size, size);
        return qrCodeBitmap;
    }
}
