package com.miloway.milonote.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.miloway.milonote.R;
import com.miloway.milonote.db.NotesProvider;
import com.miloway.milonote.listener.NoteEditEventListener;
import com.miloway.milonote.obj.MiloNote;
import com.miloway.milonote.util.MiloConstants;
import com.miloway.milonote.view.NoteEditView;

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

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MiloConstants.RESULT_TYPE_PICK_PICTURE) {
            Uri uri = data.getData();
            noteEditView.insertPicture(uri);
        }
    }
}
