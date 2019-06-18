package com.zibo.qipeng.asphalt.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.Message;
import android.support.v4.app.FragmentActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by zongshuo on 2019/3/14
 *
 * @author zongshuo ps: good luck ,ai ni o.
 */
public class SDFileHelper {
    private Context context;
    private android.os.Handler handler;
    public SDFileHelper() {
    }


    public SDFileHelper(FragmentActivity activity, android.os.Handler handler) {
        super();
        this.context = activity;
        this.handler=handler;
    }

    //往SD卡写入文件的方法
    public void savaFileToSD(String filename, byte[] bytes) throws Exception {
        //如果手机已插入sd卡,且app具有读写sd卡的权限
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String filePath = Environment.getExternalStorageDirectory() + "/Pictures/sci99";

            File dir = new File(filePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(filePath, filename);
            file.createNewFile();
            FileOutputStream fOut = new FileOutputStream(file);
            fOut.write(bytes);
            fOut.close();

            Message msg = new Message();
            msg.what = 1;
            msg.obj=file;
            handler.sendMessage(msg);
//            Toast.makeText(context, "图片已成功保存到" + filePath, Toast.LENGTH_SHORT).show();
            Logger.e("tag", "成功");
        } else {
//            Toast.makeText(context, "SD卡不存在或者不可读写", Toast.LENGTH_SHORT).show();
        }
    }

    public void loadRmoteImage(final String imageurl) {
        new Thread(){
            @Override
            public void run() {
                try {
                    URL imgUrl = new URL(imageurl);
                    // 使用HttpURLConnection打开连接
                    HttpURLConnection urlConn = (HttpURLConnection) imgUrl
                            .openConnection();
                    urlConn.setDoInput(true);
                    urlConn.setDoOutput(false);
                    urlConn.setRequestMethod("GET");
                    urlConn.setConnectTimeout(3000);
                    urlConn.setUseCaches(true);
                    urlConn.connect();
                    int code = urlConn.getResponseCode();
                    Logger.e("tag", "run: " + code);
                    // 将得到的数据转化成InputStream
                    InputStream is = urlConn.getInputStream();
                    byte[] bytesInputStream = getBytesInputStream(is);
                    savaFileToSD(System.currentTimeMillis()+".png",bytesInputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public Bitmap getBitmapInputStream(InputStream is){
        Bitmap bp;
        bp = BitmapFactory.decodeStream(is);
        return bp;
    }

    public byte[] getBytesInputStream( InputStream is) throws IOException {

        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        byte[] buff = new byte[512];
        int len;
        while ((len = is.read(buff))!= -1){
            arrayOutputStream.write(buff,0,len);
        }
        is.close();
        arrayOutputStream.close();
        return arrayOutputStream.toByteArray();
    }

    //更新图库
    public static void updatePhotoMedia(File file , Context context){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(Uri.fromFile(file));
        context.sendBroadcast(intent);
    }




    public static void writeData(String content) {
        String filePath = "/sdcard/zongshuo/";
        String fileName = System.currentTimeMillis()+"timu.txt";
        writeTxtToFile(content, filePath, fileName);
    }

    // 将字符串写入到文本文件中
    public static void writeTxtToFile(String strcontent, String filePath, String fileName) {
        //生成文件夹之后，再生成文件，不然会出错
        makeFilePath(filePath, fileName);

        String strFilePath = filePath + fileName;
        // 每次写入时，都换行写
        String strContent = strcontent + "\r\n";
        try {
            File file = new File(strFilePath);
            if (!file.exists()) {
                Logger.e("TestFile", "Create the file:" + strFilePath);
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            RandomAccessFile raf = new RandomAccessFile(file, "rwd");
            raf.seek(file.length());
            raf.write(strContent.getBytes());
            raf.close();
        } catch (Exception e) {
            Logger.e("TestFile", "Error on write File:" + e);
        }
    }


    public static File makeFilePath(String filePath, String fileName) {
        File file = null;
        makeRootDirectory(filePath);
        try {
            file = new File(filePath + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }


    public static  void makeRootDirectory(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {
            Logger.e("error:", e + "");
        }
    }

}
