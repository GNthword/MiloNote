package com.miloway.milonote.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.miloway.milonote.R;

/**
 * Created by miloway on 2018/2/8.
 */

public class LaunchActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_launch);
        Intent intent = new Intent(LaunchActivity.this,MainActivity.class);
        startActivity(intent);

    }
}
