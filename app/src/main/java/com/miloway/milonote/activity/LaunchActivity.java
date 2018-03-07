package com.miloway.milonote.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.WindowManager;

import com.miloway.milonote.R;
import com.miloway.milonote.android.MiloToast;
import com.miloway.milonote.db.NotesProvider;
import com.miloway.milonote.util.SPTool;

import java.util.Timer;
import java.util.TimerTask;

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
//        new Timer("xx").schedule(new TimerTask() {
//            @Override
//            public void run() {
//              MiloToast.makeText(LaunchActivity.this,"123456").show();
//            }
//        },3000);
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
