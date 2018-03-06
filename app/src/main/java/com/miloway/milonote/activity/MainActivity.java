package com.miloway.milonote.activity;

import android.app.Activity;
import android.os.Bundle;
import com.miloway.milonote.R;
import com.miloway.milonote.db.NotesProvider;

public class MainActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }



    @Override
    protected void onDestroy() {
        NotesProvider.getInstance().onDestroy();
        super.onDestroy();
    }
}
