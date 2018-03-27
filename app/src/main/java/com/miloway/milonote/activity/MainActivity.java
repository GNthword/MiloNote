package com.miloway.milonote.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.miloway.milonote.R;
import com.miloway.milonote.db.NotesProvider;
import com.miloway.milonote.listener.MainViewEventListener;
import com.miloway.milonote.obj.MiloNote;
import com.miloway.milonote.util.DialogFactory;
import com.miloway.milonote.util.MiloConstants;
import com.miloway.milonote.util.PermissionManager;
import com.miloway.milonote.view.MainView;

public class MainActivity extends Activity implements MainViewEventListener {

    private MainView mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainView = findViewById(R.id.main_view);
        mainView.setListener(this);
        PermissionManager.requestPermission(this, MiloConstants.RESULT_TYPE_PERMISSION_BASE);
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

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MiloConstants.RESULT_TYPE_PERMISSION_BASE) {
            for (int grantResult : grantResults) {
                if (grantResult != PackageManager.PERMISSION_GRANTED) {
                    Dialog dialog = DialogFactory.getOneBtnDialog(this, getResources().getString(R.string.permission_tip_app_exit));
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            finish();
                        }
                    });
                    dialog.show();
                    break;
                }
            }
        }
    }
}
