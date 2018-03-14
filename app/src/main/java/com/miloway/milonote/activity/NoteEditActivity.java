package com.miloway.milonote.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.miloway.milonote.R;
import com.miloway.milonote.db.NotesProvider;
import com.miloway.milonote.listener.NoteEditEventListener;
import com.miloway.milonote.obj.MiloNote;
import com.miloway.milonote.util.MiloConstants;
import com.miloway.milonote.view.NoteEditTitleView;
import com.miloway.milonote.view.RichEditView;

/**
 * Created by miloway on 2018/3/13.
 */

public class NoteEditActivity extends Activity implements NoteEditEventListener {

    private NoteEditTitleView rlTitle;
    private RichEditView richEditView;

    /**
     * 编辑对象
     */
    private MiloNote note;
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
        rlTitle = (NoteEditTitleView) findViewById(R.id.rl_title);
        richEditView = (RichEditView) findViewById(R.id.rich_edit_view);
    }

    private void initData() {
        Intent intent = getIntent();
        note = (MiloNote) intent.getSerializableExtra(MiloConstants.NOTE_OBJECT_SERIALIZE_KEY);
    }

    private void initEvent() {
        rlTitle.setListener(this);
    }

    @Override
    public void goBack() {
        NotesProvider.getInstance().saveNote(note);
        finish();
    }
}
