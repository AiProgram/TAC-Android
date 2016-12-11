package com.tac.iparttimejob.UI.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.format.DateFormat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by AiProgram on 2016/11/5.
 */

public class ImageUtils {
    public static Bitmap uri2Bitmap(Context mContext, Uri uri) {
        InputStream in = null;
        try {
            in = mContext.getContentResolver().openInputStream(uri);
            //从输入流中获取到图片
            Bitmap bm = BitmapFactory.decodeStream(in);
            in.close();
            return bm;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @param
     * @description 保存图片到手机SD卡, 并返回图片对应的文件i
     * @author ldm
     * @time 2016/7/11 9:55
     */
    public static Uri saveBitmapToSdCard(Bitmap bm) {
        //自定义图片名称
        String name = DateFormat.format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA)) + ".png";
        //定义图片存放的位置
        File tempFile = new File("/sdcard/Image/");
        if (!tempFile.exists()) {
            tempFile.mkdir();
        }
        String fileName = "/sdcard/Image/" + name;
        File pic = new File(fileName);
        try {
            FileOutputStream os = new FileOutputStream(pic);
            //对图片进行压缩
            bm.compress(Bitmap.CompressFormat.PNG, 100, os);
            os.flush();
            os.close();
            return Uri.fromFile(pic);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
