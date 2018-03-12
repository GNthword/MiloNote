package com.miloway.milonote.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.miloway.milonote.R;
import com.miloway.milonote.db.NotesProvider;
import com.miloway.milonote.obj.MiloNote;
import com.miloway.milonote.util.BackgroundTool;
import com.miloway.milonote.util.MiloConstants;
import com.miloway.milonote.util.MiloUtil;

import java.util.LinkedList;

/**
 * Created by miloway on 2018/2/23.
 */

public class NoteGridViewAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private LinkedList<MiloNote> notes;
    public NoteGridViewAdapter(Context context, LinkedList<MiloNote> notes) {
        inflater = LayoutInflater.from(context);
        this.notes = notes;
    }

    public void changeData(long parentId){
        notes = NotesProvider.getInstance().getNotes(parentId);
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        if (notes == null) {
            return 0;
        }
        return notes.size();
    }

    @Override
    public Object getItem(int position) {
        if (notes == null) {
            return null;
        }
        return notes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    /**
     * 布局数量
     */
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    /**
     * 对应位置使用的布局
     */
    @Override
    public int getItemViewType(int position) {
        if (MiloConstants.NOTE_CONTENT_TYPE_CONTENT.equals(notes.get(position).getType())){
            return 1;
        }
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        MiloNote note = notes.get(position);
        String type = notes.get(position).getType();
        if (convertView == null){
            viewHolder = new ViewHolder();
            if (MiloConstants.NOTE_CONTENT_TYPE_CONTENT.equals(type)) {
                convertView = inflater.inflate(R.layout.note_item_content, null);
            }else {
                convertView = inflater.inflate(R.layout.note_item_folder, null);
            }
            viewHolder.ivBackground = convertView.findViewById(R.id.iv_background);
            viewHolder.ivNoteIcon = convertView.findViewById(R.id.iv_note_icon);
            viewHolder.tvNoteTitle = convertView.findViewById(R.id.tv_note_title);
            viewHolder.tvNoteContent = convertView.findViewById(R.id.tv_note_content);
            viewHolder.ivAlertIcon = convertView.findViewById(R.id.iv_alert_icon);
            viewHolder.tvTime = convertView.findViewById(R.id.tv_time);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        int resId = BackgroundTool.getBackgroundResourceIdByType(note.getType(),note.getBgColor());
        viewHolder.ivBackground.setImageResource(resId);
        if (MiloConstants.NOTE_CONTENT_TYPE_CONTENT.equals(type)){
            viewHolder.ivNoteIcon.setVisibility(View.GONE);
            if (MiloConstants.NOTE_ALERT_TIME_DEFAULT_VALUE == note.getAlertDate()){
                viewHolder.ivAlertIcon.setVisibility(View.INVISIBLE);
            }else {
                viewHolder.ivAlertIcon.setVisibility(View.VISIBLE);
            }
        }else {
            viewHolder.ivNoteIcon.setVisibility(View.VISIBLE);
            viewHolder.ivAlertIcon.setVisibility(View.GONE);
        }
        setItemData(viewHolder, note);

        return convertView;
    }

    private void setItemData(ViewHolder viewHolder, MiloNote note) {
        if (MiloConstants.NOTE_CONTENT_TYPE_FOLDER.equals(note.getType())) {
            viewHolder.tvNoteTitle.setText(note.getTitle());
        }
        viewHolder.tvNoteContent.setText(note.getPreviewContent());
        viewHolder.tvTime.setText(MiloUtil.getFormatDatePreview(note.getModifiedDate()));
    }

    private class ViewHolder {
        public ImageView ivBackground;

        /**
         * 左上角图标
         */
        public ImageView ivNoteIcon;

        /**
         * 标题，仅文件夹有
         */
        public TextView tvNoteTitle;

        public TextView tvNoteContent;

        public ImageView ivAlertIcon;

        public TextView tvTime;


    }
}
