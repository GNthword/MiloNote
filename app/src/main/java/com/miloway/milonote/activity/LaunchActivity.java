package com.miloway.milonote.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.miloway.milonote.R;
import com.miloway.milonote.db.NotesProvider;
import com.miloway.milonote.util.SPTool;

/**
 * Created by miloway on 2018/2/8.
 */

public class LaunchActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_launch);

        init();
        prepareData();
        next();
    }

    private void init(){
        boolean isInit = SPTool.get(this, SPTool.SP_FILE_LAUNCH_CONFIG, SPTool.SP_KEY_LAUNCH_INIT, false);
        if (!isInit){
            NotesProvider notesProvider = NotesProvider.getInstance();
            notesProvider.init();
            SPTool.put(this, SPTool.SP_FILE_LAUNCH_CONFIG, SPTool.SP_KEY_LAUNCH_INIT, true);
        }
    }

    private void prepareData(){
        NotesProvider notesProvider = NotesProvider.getInstance();
        notesProvider.prepareData();
    }

    private void next(){
        Intent intent = new Intent(LaunchActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
