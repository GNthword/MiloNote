package com.miloway.milonote.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.miloway.milonote.R;
import com.miloway.milonote.db.NotesProvider;
import com.miloway.milonote.listener.MainViewEventListener;
import com.miloway.milonote.obj.MiloNote;
import com.miloway.milonote.util.MiloConstants;
import com.miloway.milonote.view.MainView;

public class MainActivity extends Activity implements MainViewEventListener {

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
    public void gotoNoteEdit(long parentId) {
        gotoNoteEdit(NotesProvider.getInstance().newNote(parentId));
    }

    @Override
    public void gotoNoteEdit(MiloNote note) {
        Intent intent = new Intent(MainActivity.this, NoteEditActivity.class);
        intent.putExtra(MiloConstants.NOTE_OBJECT_SERIALIZE_KEY,note);
        startActivity(intent);
    }
}
