package com.miloway.milonote.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.miloway.milonote.android.MiloApplication;
import com.miloway.milonote.obj.MiloNote;
import com.miloway.milonote.util.MiloConstants;
import com.miloway.milonote.util.MiloUtil;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Created by miloway on 2018/2/23.
 */

public class NotesProvider {

    private SQLiteDatabase database;
    private static NotesProvider provider = new NotesProvider();
    private Context context;

    /*
     * 数据结构
     * 1    2    3
     * 1.1  1.2
     * 1.1.1  1.1.2
     * 1.1.1.1  1.1.1.2
     */
    /**
     * firstPageNotes 用于快速显示
     * cacheNotes 则为上一次选中的文件夹
     */
    private LinkedList<MiloNote> firstPageNotes;
    private long cacheParentId;
    private LinkedList<MiloNote> cacheNotes;

    private NotesProvider(){
        context = MiloApplication.getMiloApplication();
        NoteSQLiteOpenHelper helper = new NoteSQLiteOpenHelper(context);
        database = helper.getWritableDatabase();
    }

    public static NotesProvider getInstance(){
        return provider;
    }

    /**
     * 初始化
     */
    public void init(){
        String content = "INSERT INTO note_content values ("
                + "'0',"
                + "0,"
                + "0,"
                + "'content',"
                + "'" + MiloUtil.getCurrentFormatDate()+"',"
                + "'" +  MiloUtil.getCurrentFormatDate()+"',"
                + "'This is a sample',"
                + "'This is a sample',"
                + "'null',"
                + "'yellow'"
                + ")";
        database.execSQL(content);
    }

    /**
     * 准备数据
     */
    public void prepareData(){
        firstPageNotes = getData(0);
        cacheNotes = new LinkedList<MiloNote>();
    }

    private LinkedList<MiloNote> getData(long parentId){
        LinkedList<MiloNote> temp = new LinkedList<MiloNote>();
        String sql = "SELECT * FROM note_content where parent_id = "+parentId;
        Cursor cursor = database.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            while (cursor.moveToNext()){
                temp.add(getObject(cursor));
            }
        }
        if (!cursor.isClosed()){
            cursor.close();
        }
        sortNotes(temp);
        return temp;
    }

    private MiloNote getObject(Cursor cursor){
        if (cursor == null || cursor.isClosed()){
            return null;
        }
        MiloNote note = new MiloNote();
        note.setId(cursor.getLong(MiloConstants.NOTE_COLUMN_INDEX_ID));
        note.setParentId(cursor.getLong(MiloConstants.NOTE_COLUMN_INDEX_PARENT_ID));
        note.setPosition(cursor.getLong(MiloConstants.NOTE_COLUMN_INDEX_POSITION));
        note.setType(cursor.getString(MiloConstants.NOTE_COLUMN_INDEX_TYPE));
        note.setCreatedDate(cursor.getString(MiloConstants.NOTE_COLUMN_INDEX_CREATE_DATE));
        note.setModifiedDate(cursor.getString(MiloConstants.NOTE_COLUMN_INDEX_MODIFIED_DATE));
        note.setPreviewContent(cursor.getString(MiloConstants.NOTE_COLUMN_INDEX_PREVIEW_CONTENT));
        note.setContent(cursor.getString(MiloConstants.NOTE_COLUMN_INDEX_CONTENT));
        note.setAlertDate(cursor.getString(MiloConstants.NOTE_COLUMN_INDEX_ALERT_DATE));
        note.setBgColor(cursor.getString(MiloConstants.NOTE_COLUMN_INDEX_BG_COLOR));
        return note;
    }

    /**
     * 排序
     */
    private void sortNotes(LinkedList<MiloNote> notes) {
        Collections.sort(notes, new Comparator<MiloNote>() {
            @Override
            public int compare(MiloNote o1, MiloNote o2) {
                if (o1.getPosition() == o2.getPosition()){
                    return 1;
                }
                return (int) (o1.getPosition() - o2.getPosition());
            }
        });
    }

//    private class RunTask implements Runnable/*,Callable<Object>,Future<Object>*/{
//
//        @Override
//        public void run() {
//
//        }
//    }


    public LinkedList<MiloNote> getNotes(long parentId){
        if (parentId == 0){
            return firstPageNotes;
        }
        if (parentId == cacheParentId){
            return cacheNotes;
        }
        cacheParentId = parentId;
        cacheNotes = getData(parentId);
        return cacheNotes;
    }



    public void onDestroy(){
        if (database != null){
            database.close();
        }
        firstPageNotes.clear();
        cacheNotes.clear();
        firstPageNotes = null;
        cacheNotes = null;
        cacheParentId = 0;
        context = null;
    }

}
