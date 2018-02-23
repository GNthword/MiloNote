package com.miloway.milonote.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.miloway.milonote.R;
import com.miloway.milonote.db.NotesProvider;
import com.miloway.milonote.view.NoteGridView;

public class MainActivity extends Activity {

    private LinearLayout llNewNote;
    private NoteGridView noteGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initEvent();
    }


    private void initView() {
        llNewNote = (LinearLayout) findViewById(R.id.ll_new_note);
        noteGridView = findViewById(R.id.grid_view);
    }

    private void initData() {

    }

    private void initEvent() {

    }


    @Override
    protected void onDestroy() {
        NotesProvider.getInstance().onDestroy();
        super.onDestroy();
    }
}
