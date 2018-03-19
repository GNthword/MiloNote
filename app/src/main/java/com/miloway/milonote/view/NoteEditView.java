package com.miloway.milonote.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.miloway.milonote.R;
import com.miloway.milonote.listener.NoteEditEventListener;
import com.miloway.milonote.obj.MiloNote;
import com.miloway.milonote.util.BackgroundTool;
import com.miloway.milonote.util.MiloUtil;

/**
 * Created by miloway on 2018/3/13.
 */

public class NoteEditView extends RelativeLayout {

    private NoteEditTitleView rlTitle;
    private RichEditView richEditView;
    private TextView tvCreateTime;

    public NoteEditView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoteEditView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();
    }

    private void initView() {
        rlTitle = (NoteEditTitleView) findViewById(R.id.rl_title);
        richEditView = (RichEditView) findViewById(R.id.rich_edit_view);
        tvCreateTime = (TextView) findViewById(R.id.tv_create_time);
    }

    /**
     * 初始化文本数据
     */
    public void setData(MiloNote note, String content) {
        if (note == null) {
            return;
        }
        rlTitle.setModifyTime(note.getModifiedDate());
        richEditView.setText(content);
        tvCreateTime.setText(MiloUtil.getFormatDate(note.getCreatedDate()));
    }

    /**
     * 设置背景颜色
     */
    public void changeBgColor(String colorType) {
        int color;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            color = getResources().getColor(BackgroundTool.getTitleBgColorResId(colorType),null);
        }else {
            color = getResources().getColor(BackgroundTool.getTitleBgColorResId(colorType));
        }
        rlTitle.setBgColor(colorType);
        rlTitle.setBackgroundColor(color);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            color = getResources().getColor(BackgroundTool.getContentBgColorResId(colorType),null);
        }else {
            color = getResources().getColor(BackgroundTool.getContentBgColorResId(colorType));
        }
        richEditView.setBackgroundColor(color);
        tvCreateTime.setBackgroundColor(color);
    }

    /**
     * 获取内容
     */
    public String getContent() {
        return richEditView.getContent();
    }

    /**
     * 获取内容预览
     */
    public String getPreviewContent() {
        return richEditView.getPreviewContent();
    }

    public void setListener(NoteEditEventListener listener) {
        rlTitle.setListener(listener);
    }

}
