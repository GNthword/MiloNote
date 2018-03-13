package com.miloway.milonote.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.miloway.milonote.R;
import com.miloway.milonote.db.NotesProvider;
import com.miloway.milonote.view.MainView;

public class MainActivity extends Activity implements MainView.ActivityEventListener {

    private MainView mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainView = findViewById(R.id.main_view);
        mainView.setListener(this);
    }



    @Override
    protected void onDestroy() {
        NotesProvider.getInstance().onDestroy();
        super.onDestroy();
    }

    @Override
    public void gotoNewNoteEdit() {
        Intent intent = new Intent(MainActivity.this, NoteEditActivity.class);
        startActivity(intent);
    }
}
