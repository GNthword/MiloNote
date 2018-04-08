package com.miloway.milonote.util;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by miloway on 2018/4/8.
 */

public class PictureAndCameraManager {

    private Activity activity;
    private String savePath;
    private Uri saveUri;

    public PictureAndCameraManager(Activity activity) {
        this.activity = activity;
    }

    /**
     * 选择照片
     */
    public void pickPicture() {
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        activity.startActivityForResult(intent,MiloConstants.RESULT_TYPE_PICK_PICTURE);
    }

    /**
     * 打开相机
     */
    public void openCamera() {
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String name = MiloUtil.getCameraPictureName();
        File dir = new File(MiloUtil.getCameraPictureSavePath());
        if (!dir.exists()) {
            dir.mkdir();
        }
        savePath = MiloUtil.getCameraPictureSavePath() + name;
        File file = new File(savePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                LogTool.printStackTrace(e);
            }
        }
        ContentValues contentValues = new ContentValues(2);

        //contentValues.put(MediaStore.Images.Media.DATA, savePath);
        //如果想拍完存在系统相机的默认目录,改为
        contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, name);

        contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        saveUri = activity.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);

        //Uri uri = Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,saveUri);
        activity.startActivityForResult(intent,MiloConstants.RESULT_TYPE_OPEN_CAMERA);
    }


    /**
     * 处理相机返回
     */
    public String dealPictureFromCamera() {
        if (activity == null || saveUri == null || savePath == null) {
            return null;
        }

        String[] projection = {
                MediaStore.Images.Media.DATA
        };
        String path = null;
        Cursor c = activity.getContentResolver().query(saveUri, projection, null, null, null);
        if (c != null) {
            if (c.moveToFirst()) {
                path = c.getString(0);
            }
            c.close();
        }
        if (path == null) {
            return null;
        }

        FileOutputStream fos = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(path);
            fos = new FileOutputStream(savePath);
            byte[] b = new byte[1024];
            while (fis.read(b) != -1) {
                fos.write(b);
            }
            fos.flush();
        } catch (IOException e) {
            LogTool.printStackTrace(e);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    LogTool.printStackTrace(e);
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    LogTool.printStackTrace(e);
                }
            }
        }
        //复制到私有目录
        File file = new File(path);
        file.delete();
        File file1 = new File(savePath);
        if (file1.length() > 0) {
            return savePath;
        }
        return null;
    }


    public void destroy() {
        activity = null;
    }

}
