package com.miloway.milonote.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.miloway.milonote.android.MiloApplication;
import com.miloway.milonote.obj.MiloNote;
import com.miloway.milonote.util.BackgroundTool;
import com.miloway.milonote.util.LogTool;
import com.miloway.milonote.util.MiloConstants;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Created by miloway on 2018/2/23.
 */

public class NotesProvider {

    private SQLiteDatabase database;
    private static NotesProvider provider = new NotesProvider();

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

    private static final String NOTE_CONTENT_TABLE_NAME = "note_content";
    private static final String NOTE_CONTENT_DETAIL_TABLE_NAME = "note_content_detail";

    private NotesProvider(){
        NoteSQLiteOpenHelper helper = new NoteSQLiteOpenHelper(MiloApplication.getMiloApplication());
        database = helper.getWritableDatabase();
    }

    public static NotesProvider getInstance(){
        return provider;
    }

    /**
     * 初始化
     */
    public void init(){
        long time = System.currentTimeMillis();
        String content = "INSERT INTO " + NOTE_CONTENT_TABLE_NAME + " values ("
                + "0,"
                + "0,"
                + "0,"
                + "'content',"
                + "" + time+","
                + "" +  time+","
                + "'',"
                + "'This is a sample',"
                + "-1,"
                + "'yellow',"
                + "'none',"
                + "''"
                + ")";
        String content_detail = "INSERT INTO " + NOTE_CONTENT_DETAIL_TABLE_NAME + " values ("
                + "0,"
                + "'This is a sample\n\n\n\n\nThis is a sample'"
                + ")";
        database.execSQL(content);
        database.execSQL(content_detail);
    }

    /**
     * 准备数据
     */
    public void prepareData(){
        firstPageNotes = getData(MiloConstants.NOTE_FOLDER_PARENT_ID_TOP_LEVEL);
        cacheNotes = new LinkedList<MiloNote>();
    }

    private LinkedList<MiloNote> getData(long parentId){
        LinkedList<MiloNote> temp = new LinkedList<MiloNote>();
        String sql = "SELECT * FROM note_content where parent_id = "+parentId;
        Cursor cursor = database.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            temp.add(getObject(cursor));
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
        note.setCreatedDate(cursor.getLong(MiloConstants.NOTE_COLUMN_INDEX_CREATE_DATE));
        note.setModifiedDate(cursor.getLong(MiloConstants.NOTE_COLUMN_INDEX_MODIFIED_DATE));
        note.setPreviewContent(cursor.getString(MiloConstants.NOTE_COLUMN_INDEX_PREVIEW_CONTENT));
        note.setTitle(cursor.getString(MiloConstants.NOTE_COLUMN_INDEX_TITLE));
        note.setAlertDate(cursor.getLong(MiloConstants.NOTE_COLUMN_INDEX_ALERT_DATE));
        note.setBgColor(cursor.getString(MiloConstants.NOTE_COLUMN_INDEX_BG_COLOR));
        note.setPasswordType(cursor.getString(MiloConstants.NOTE_COLUMN_INDEX_PASSWORD_TYPE));
        note.setPassword(cursor.getString(MiloConstants.NOTE_COLUMN_INDEX_PASSWORD));
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

    /**
     * 生成一个新的MiloNote对象<br/>
     * 不会设置id、parent_id、position 和 modifiedDate
     */
    public MiloNote newNote(long parentId){
        return newNote(parentId, MiloConstants.NOTE_CONTENT_TYPE_CONTENT);
    }

    /**
     * 生成一个新的MiloNote对象<br/>
     * 不会设置id、parent_id、position 和 modifiedDate
     */
    public MiloNote newNote(long parentId, String type){
        MiloNote note = new MiloNote();
        long time = System.currentTimeMillis();
        note.setId(MiloConstants.NOTE_ID_DEFAULT_VALUE);
        note.setParentId(parentId);
        note.setType(type);
        note.setCreatedDate(time);
        note.setModifiedDate(time);
        note.setPreviewContent("");
        note.setTitle("");
        note.setAlertDate(MiloConstants.NOTE_ALERT_TIME_DEFAULT_VALUE);
        note.setBgColor(BackgroundTool.NOTE_BACKGROUND_COLOR_YELLOW);
        note.setPasswordType("");
        note.setPassword("");
        return note;
    }

    /**
     * 是否新建的便签
     */
    public boolean isNewNote(MiloNote note) {
        if (note != null) {
            if (note.getId() == MiloConstants.NOTE_ID_DEFAULT_VALUE) {
                return true;
            }
        }
        return false;
    }

    /**
     * 保存便签
     */
    public boolean saveNote(MiloNote note, String content) {
        if (note == null || content == null) {
            return false;
        }
        //新建后 未修改，不保存
        if (note.getId() == MiloConstants.NOTE_ID_DEFAULT_VALUE && "".equals(note.getPreviewContent())) {
            return false;
        }
        //数据错误
        if (note.getType() == null || note.getCreatedDate() == 0) {
            return false;
        }
        boolean state = false;
        note.setModifiedDate(System.currentTimeMillis());
        //保存新建便签
        String sql = "";
        if (note.getId() == MiloConstants.NOTE_ID_DEFAULT_VALUE) {
            //插入
            sql = getInsertContentSql(note);
            database.beginTransaction();
            try {
                database.execSQL(sql);
                long id = getTopId(NOTE_CONTENT_TABLE_NAME);
                if (id != MiloConstants.NOTE_ID_DEFAULT_VALUE) {
                    sql = getInsertContentDetailSql(id, content);
                    database.execSQL(sql);
                    database.setTransactionSuccessful();
                    state = true;
                }
            }catch (Exception e){
                LogTool.printStackTrace(e);
            }finally {
                database.endTransaction();
            }

        }else {
            //更新
            sql = getUpdateContentSql(note);
            database.beginTransaction();
            try {
                database.execSQL(sql);
                sql = getUpdateContentDetailSql(note.getId(), content);
                database.execSQL(sql);
                database.setTransactionSuccessful();
                state = true;
            }catch (Exception e){
                LogTool.printStackTrace(e);
            }finally {
                database.endTransaction();
            }
        }

        return state;
    }

    /**
     * 删除便签
     */
    public boolean deleteNote(long id) {
        boolean state = false;
        database.beginTransaction();
        try {
            String sql = getDeleteContentSql(id, NOTE_CONTENT_TABLE_NAME);
            database.execSQL(sql);
            sql = getDeleteContentSql(id, NOTE_CONTENT_DETAIL_TABLE_NAME);
            database.execSQL(sql);
            state = true;
        }catch (Exception e){
            LogTool.printStackTrace(e);
        }finally {
            database.endTransaction();
        }
        return state;
    }

    private String getInsertContentSql(MiloNote note) {
        String sql = "INSERT INTO " + NOTE_CONTENT_TABLE_NAME + " VALUES("
                + "null,"
                + note.getParentId() + ","
                + note.getPosition() + ","
                + "'" + note.getType() + "',"
                + note.getCreatedDate() + ","
                + note.getModifiedDate() + ","
                + "'" + note.getTitle() + "',"
                + "'" + note.getPreviewContent() + "',"
                + note.getAlertDate() + ","
                + "'" + note.getBgColor() + "',"
                + "'" + note.getPasswordType() + "',"
                + "'" +  note.getPassword() + "'"
                + ")";
        return sql;
    }

    private String getInsertContentDetailSql(long id, String content) {
        String sql = "INSERT INTO " + NOTE_CONTENT_DETAIL_TABLE_NAME + " VALUES("
                + id + ","
                + "'" + content + "'"
                + ")";
        return sql;
    }

    private String getUpdateContentSql(MiloNote note) {
        String sql = "UPDATE " + NOTE_CONTENT_TABLE_NAME + " SET "
                + "parent_id =" + note.getParentId() + ","
                + "position=" + note.getPosition() + ","
                + "modified_date=" + note.getModifiedDate() + ","
                + "title='" + note.getTitle() + "',"
                + "preview_content'=" + note.getPreviewContent() + "',"
                + "alert_date=" + note.getAlertDate() + ","
                + "bg_color='" + note.getBgColor() + "',"
                + "password_type='" + note.getPasswordType() + "',"
                + "password='" + note.getPassword() + "'"
                + " WHERE id = " + note.getId();
        return sql;
    }

    private String getUpdateContentDetailSql(long id, String content) {
        String sql = "UPDATE " + NOTE_CONTENT_DETAIL_TABLE_NAME + " SET "
                + "content ='" + content + "'"
                + " WHERE id = " + id;
        return sql;
    }

    private long getTopId(String tableName){
        if (TextUtils.isEmpty(tableName)) {
            tableName = NOTE_CONTENT_TABLE_NAME;
        }
        String sql = "SELECT top 1 id FROM " + tableName + " ORDER BY DESC";

        long id = MiloConstants.NOTE_ID_DEFAULT_VALUE;
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor.moveToFirst()){
            id = cursor.getLong(0);
        }
        cursor.close();

        return id;
    }

    private String getDeleteContentSql(long id, String tableName){
        if (TextUtils.isEmpty(tableName)) {
            tableName = NOTE_CONTENT_TABLE_NAME;
        }
        String sql = "DELETE FROM " + tableName
                + "WHERE id = " + id;
        return sql;
    }

    /**
     * 内存回收
     */
    public void onDestroy(){
        if (database != null){
            database.close();
        }
        firstPageNotes.clear();
        cacheNotes.clear();
        firstPageNotes = null;
        cacheNotes = null;
        cacheParentId = 0;
    }

}
