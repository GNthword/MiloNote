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
                + " created_date INTEGER NOT NULL,"
                + " modified_date INTEGER NOT NULL,"
                + " title TEXT,"
                + " preview_content TEXT,"
                + " alert_date INTEGER DEFAULT -1,"
                + " bg_color TEXT DEFAULT yellow,"
                + " password_type TEXT DEFAULT none,"
                + " password TEXT"
                + ")";

        /*
         * 内容详细
         */
        String detail = "CREATE TABLE note_content_detail "
                + "( id INTEGER PRIMARY KEY ,"
                + " content TEXT"
                + ")";

        sqLiteDatabase.execSQL(content);
        sqLiteDatabase.execSQL(detail);
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
