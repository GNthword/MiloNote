package com.miloway.milonote.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import com.miloway.milonote.R;
import com.miloway.milonote.db.NotesProvider;
import com.miloway.milonote.listener.NoteEditEventListener;
import com.miloway.milonote.obj.MiloNote;
import com.miloway.milonote.util.MiloConstants;
import com.miloway.milonote.util.MiloUtil;
import com.miloway.milonote.view.NoteEditView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by miloway on 2018/3/13.<br/>
 * Activity 负债处理Activity直接事务（传递数据、保存数据、启动/结束Activity、为View设置初始值）
 */

public class NoteEditActivity extends Activity implements NoteEditEventListener {

    private NoteEditView noteEditView;

    /**
     * 编辑对象
     */
    private MiloNote note;
    private String content;
    private String savePath;
    private Uri saveUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);
        initView();
        initData();
        initEvent();
        if (note == null) {
            finish();
        }
    }

    private void initView() {
        noteEditView = (NoteEditView) findViewById(R.id.note_edit_view);
    }

    private void initData() {
        Intent intent = getIntent();
        note = (MiloNote) intent.getSerializableExtra(MiloConstants.NOTE_OBJECT_SERIALIZE_KEY);
        if (NotesProvider.getInstance().isNewNote(note)) {
            content = "";
        }
        noteEditView.setData(note,content);
        noteEditView.changeBgColor(note.getBgColor());
    }

    private void initEvent() {
        noteEditView.setListener(this);
    }

    @Override
    public void goBack() {
        content = noteEditView.getContent();
        note.setPreviewContent(noteEditView.getPreviewContent());
        NotesProvider.getInstance().saveNote(note,content);
        finish();
    }

    @Override
    public void changeColor(String bgColor) {
        note.setBgColor(bgColor);
        noteEditView.changeBgColor(note.getBgColor());
    }

    @Override
    public void pickPicture() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,MiloConstants.RESULT_TYPE_PICK_PICTURE);
    }

    @Override
    public void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String name = MiloUtil.getCameraPictureName();
        savePath = MiloUtil.getCameraPictureSavePath() + name;
        File file = new File(savePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ContentValues contentValues = new ContentValues(2);

        //contentValues.put(MediaStore.Images.Media.DATA, savePath);
        //如果想拍完存在系统相机的默认目录,改为
        contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, name);

        contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        saveUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);

        //Uri uri = Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,saveUri);
        startActivityForResult(intent,MiloConstants.RESULT_TYPE_OPEN_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MiloConstants.RESULT_TYPE_PICK_PICTURE) {
            if (data != null) {
                Uri uri = data.getData();
                noteEditView.insertPicture(MiloUtil.getPictureFullPath(this, uri));
            }
        }else if (requestCode == MiloConstants.RESULT_TYPE_OPEN_CAMERA) {
            if (data == null && saveUri != null && savePath != null) {
                dealPictureFromCamera();
            }
        }
    }

    private void dealPictureFromCamera() {
        String[] projection = {
                MediaStore.Images.Media.DATA
        };
        String path = null;
        Cursor c = getContentResolver().query(saveUri, projection, null, null, null);
        if (c != null) {
            if (c.moveToFirst()) {
                path = c.getString(0);
            }
            c.close();
        }
        if (path == null) {
            return;
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
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //复制到私有目录
        File file = new File(path);
        file.delete();
        File file1 = new File(savePath);
        if (file1.length() > 0) {
            noteEditView.insertPicture(savePath);
        }
    }

}
