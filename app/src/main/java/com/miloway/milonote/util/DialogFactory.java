package com.miloway.milonote.util;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.miloway.milonote.R;

/**
 * Created by miloway on 2018/3/16.
 */

public class DialogFactory {

    public static Dialog getOneBtnDialog(Context context, String message) {
        return getOneBtnDialog(context, null, message, null);
    }


    public static Dialog getOneBtnDialog(Context context, String title, String message, String ok) {
        if (context == null) {
            return null;
        }
        if (title == null) {
            title = context.getResources().getString(R.string.dialog_title);
        }
        if (ok == null) {
            ok = context.getResources().getString(R.string.dialog_btn_ok);
        }


        final Dialog dialog = new Dialog(context, R.style.BaseDialog);
        View contentView = LayoutInflater.from(context).inflate(R.layout.common_dialog, null);
        TextView tvTitle = contentView.findViewById(R.id.tv_dialog_title);
        TextView tvContent = contentView.findViewById(R.id.tv_dialog_content);

        tvTitle.setText(title);
        tvContent.setText(message);

        Button btnOk = contentView.findViewById(R.id.btn_ok);
        btnOk.setBackgroundResource(R.drawable.common_dialog_one_btn_background);
        btnOk.setText(ok);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        Button btnCancel = contentView.findViewById(R.id.btn_cancel);
        btnCancel.setVisibility(View.GONE);
        contentView.findViewById(R.id.line3).setVisibility(View.GONE);

        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);

        int width = context.getResources().getDimensionPixelSize(R.dimen.dialog_width);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setContentView(contentView, params);
        return dialog;
    }


    public static Dialog getTwoBtnDialog(Context context, String message) {
        return getTwoBtnDialog(context, null, message, null, null);
    }

    public static Dialog getTwoBtnDialog(Context context, String title, String message, String ok, String cancel) {
        if (context == null) {
            return null;
        }
        if (title == null) {
            title = context.getResources().getString(R.string.dialog_title);
        }
        if (ok == null) {
            ok = context.getResources().getString(R.string.dialog_btn_ok);
        }
        if (cancel == null) {
            cancel = context.getResources().getString(R.string.dialog_btn_cancel);
        }


        final Dialog dialog = new Dialog(context, R.style.BaseDialog);
        View contentView = LayoutInflater.from(context).inflate(R.layout.common_dialog, null);
        TextView tvTitle = contentView.findViewById(R.id.tv_dialog_title);
        TextView tvContent = contentView.findViewById(R.id.tv_dialog_content);

        tvTitle.setText(title);
        tvContent.setText(message);

        Button btnCancel = contentView.findViewById(R.id.btn_cancel);
        btnCancel.setText(cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        Button btnOk = contentView.findViewById(R.id.btn_ok);
        btnOk.setText(ok);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);

        int width = context.getResources().getDimensionPixelSize(R.dimen.dialog_width);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setContentView(contentView, params);
        return dialog;
    }

    public static Dialog getInsertDialog(Context context) {
        final Dialog dialog = new Dialog(context, R.style.BaseDialog);
        View contentView = LayoutInflater.from(context).inflate(R.layout.note_edit_title_view_insert_dialog, null);

        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);

        int width = context.getResources().getDimensionPixelSize(R.dimen.dialog_width);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setContentView(contentView, params);
        return dialog;
    }
}
