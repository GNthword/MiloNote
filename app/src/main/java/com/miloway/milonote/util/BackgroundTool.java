package com.miloway.milonote.util;

import com.miloway.milonote.R;

/**
 * Created by miloway on 2018/3/5.
 */

public class BackgroundTool {

    /*
     * 便签背景色
     */
    public static final String NOTE_BACKGROUND_COLOR_BLUE = "blue";
    public static final String NOTE_BACKGROUND_COLOR_GREEN = "green";
    public static final String NOTE_BACKGROUND_COLOR_RED = "red";
    public static final String NOTE_BACKGROUND_COLOR_WHITE = "white";
    public static final String NOTE_BACKGROUND_COLOR_YELLOW = "yellow";

//    private static final int resYellowTd = R.drawable.grid_background_yellow;

    public static int getBackgroundResourceIdByType(String noteType, String colorType) {
        if (noteType == null || colorType == null) {
            return R.drawable.grid_background_yellow;
        }
        int resId = R.drawable.grid_background_yellow;
        boolean isContentType = MiloConstants.NOTE_CONTENT_TYPE_CONTENT.equals(noteType);
        switch (colorType) {
            case NOTE_BACKGROUND_COLOR_YELLOW:
                if (isContentType) {
                    resId = R.drawable.grid_background_yellow;
                }else {
                    resId = R.drawable.grid_folder_background_yellow;
                }
                break;
            case NOTE_BACKGROUND_COLOR_BLUE:
                if (isContentType) {
                    resId = R.drawable.grid_background_blue;
                }else {
                    resId = R.drawable.grid_folder_background_blue;
                }
                break;
            case NOTE_BACKGROUND_COLOR_GREEN:
                if (isContentType) {
                    resId = R.drawable.grid_background_green;
                }else {
                    resId = R.drawable.grid_folder_background_green;
                }
                break;
            case NOTE_BACKGROUND_COLOR_RED:
                if (isContentType) {
                    resId = R.drawable.grid_background_red;
                }else {
                    resId = R.drawable.grid_folder_background_red;
                }
                break;
            case NOTE_BACKGROUND_COLOR_WHITE:
                if (isContentType) {
                    resId = R.drawable.grid_background_white;
                }else {
                    resId = R.drawable.grid_folder_background_white;
                }
                break;
        }
        return resId;
    }

    public static int getTitleBgColorResId(String colorType) {
        if (colorType == null) {
            return R.color.note_edit_view_title_bar_bg_yellow;
        }

        int resId = R.color.note_edit_view_title_bar_bg_yellow;
        switch (colorType) {
            case NOTE_BACKGROUND_COLOR_YELLOW:
                resId = R.color.note_edit_view_title_bar_bg_yellow;
                break;
            case NOTE_BACKGROUND_COLOR_BLUE:
                resId = R.color.note_edit_view_title_bar_bg_blue;
                break;
            case NOTE_BACKGROUND_COLOR_GREEN:
                resId = R.color.note_edit_view_title_bar_bg_green;
                break;
            case NOTE_BACKGROUND_COLOR_RED:
                resId = R.color.note_edit_view_title_bar_bg_red;
                break;
            case NOTE_BACKGROUND_COLOR_WHITE:
                resId = R.color.note_edit_view_title_bar_bg_white;
                break;
        }
        return resId;
    }

    public static int getContentBgColorResId(String colorType) {
        if (colorType == null) {
            return R.color.note_edit_view_content_bg_yellow;
        }

        int resId = R.color.note_edit_view_content_bg_yellow;
        switch (colorType) {
            case NOTE_BACKGROUND_COLOR_YELLOW:
                resId = R.color.note_edit_view_content_bg_yellow;
                break;
            case NOTE_BACKGROUND_COLOR_BLUE:
                resId = R.color.note_edit_view_content_bg_blue;
                break;
            case NOTE_BACKGROUND_COLOR_GREEN:
                resId = R.color.note_edit_view_content_bg_green;
                break;
            case NOTE_BACKGROUND_COLOR_RED:
                resId = R.color.note_edit_view_content_bg_red;
                break;
            case NOTE_BACKGROUND_COLOR_WHITE:
                resId = R.color.note_edit_view_content_bg_white;
                break;
        }
        return resId;
    }



    
}
