package com.miloway.milonote.listener;

import com.miloway.milonote.obj.MiloNote;

/**
 * Created by miloway on 2018/3/13.
 */

public interface MainViewEventListener {
    void gotoNoteEdit(long parentId);
    void gotoNoteEdit(MiloNote note);
}
