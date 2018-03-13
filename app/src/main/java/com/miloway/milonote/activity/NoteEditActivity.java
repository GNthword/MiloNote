package com.miloway.milonote.activity;

import android.app.Activity;
import android.os.Bundle;

import com.miloway.milonote.R;
import com.miloway.milonote.listener.NoteEditEventListener;
import com.miloway.milonote.view.NoteEditTitleView;
import com.miloway.milonote.view.RichEditView;

/**
 * Created by miloway on 2018/3/13.
 */

public class NoteEditActivity extends Activity implements NoteEditEventListener {

    private NoteEditTitleView rlTitle;
    private RichEditView richEditView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);
        initView();
        initEvent();
    }

    private void initView() {
        rlTitle = (NoteEditTitleView) findViewById(R.id.rl_title);
        richEditView = (RichEditView) findViewById(R.id.rich_edit_view);
    }

    private void initEvent() {
        rlTitle.setListener(this);
    }

    @Override
    public void goBack() {
        finish();
    }
}
