package com.miloway.milonote.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.miloway.milonote.db.NotesProvider;
import com.miloway.milonote.obj.MiloNote;

import java.util.LinkedList;

/**
 * Created by miloway on 2018/2/23.
 */

public class NoteAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private LinkedList<MiloNote> notes;
    public NoteAdapter(Context context, LinkedList<MiloNote> notes) {
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
