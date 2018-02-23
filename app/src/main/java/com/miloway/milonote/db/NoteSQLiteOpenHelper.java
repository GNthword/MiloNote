package com.miloway.milonote.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by miloway on 2018/2/22.
 */

public class NoteSQLiteOpenHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "milo_note.db";
    private static int DATABASE_VERSION = 1;

    public NoteSQLiteOpenHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        /*
         * 内容
         */
        String content = "CREATE TABLE note_content "
                + "( id INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + " parent_id INTEGER NOT NULL,"
                + " position INTEGER NOT NULL,"
                + " type TEXT NOT NULL,"    //文件夹 还是内容
                + " created_date TEXT NOT NULL,"
                + " modified_date TEXT NOT NULL,"
                + " preview_content TEXT,"
                + " content TEXT,"
                + " alert_date TEXT,"
                + " bg_color TEXT DEFAULT yellow,"
                + ")";

        sqLiteDatabase.execSQL(content);
        /*
         * json方式
         */
//        String content = "CREATE TABLE note_content_json "
//                + "( id INTEGER PRIMARY KEY,"
//                + " object TEXT,"
//                + ")";
//
//        sqLiteDatabase.execSQL(content);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
