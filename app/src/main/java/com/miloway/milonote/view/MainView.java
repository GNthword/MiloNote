package com.miloway.milonote.view;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.miloway.milonote.R;
import com.miloway.milonote.activity.MainActivity;
import com.miloway.milonote.activity.NoteEditActivity;
import com.miloway.milonote.db.NotesProvider;
import com.miloway.milonote.listener.MainViewEventListener;
import com.miloway.milonote.obj.MiloNote;
import com.miloway.milonote.util.MiloConstants;

import java.util.LinkedList;

/**
 * Created by miloway on 2018/3/6.
 */

public class MainView extends RelativeLayout implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, View.OnClickListener {

    private LinearLayout llNewNote;
    private NoteGridView noteGridView;
    private NoteGridViewAdapter adapter;

    private MainViewEventListener listener;

    public MainView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MainView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        llNewNote = (LinearLayout) findViewById(R.id.ll_new_note);
        noteGridView = findViewById(R.id.grid_view);
    }

    private void initData() {
        LinkedList<MiloNote> notes = NotesProvider.getInstance().getNotes(MiloConstants.NOTE_FOLDER_PARENT_ID_TOP_LEVEL);
        adapter = new NoteGridViewAdapter(getContext(), notes);
        noteGridView.setAdapter(adapter);
    }

    private void initEvent() {
        llNewNote.setOnClickListener(this);
        noteGridView.setOnItemClickListener(this);
        noteGridView.setOnItemLongClickListener(this);
    }

    /**
     * gridView 点击事件
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    /**
     * gridView 长按事件
     */
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        return true;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.ll_new_note) {
            if (listener != null) {
                listener.gotoNewNoteEdit();
            }
        }
    }

    public void setListener(MainViewEventListener listener) {
        this.listener = listener;
    }

}
