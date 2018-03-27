package com.miloway.milonote.util;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.view.View;
import com.miloway.milonote.R;
import java.util.ArrayList;

/**
 * Created by miloway on 2018/3/27.
 */

public class PermissionManager {


    public static final String[] READ_AND_WRITE_EXTERNAL_STORAGE = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public static void requestPermission(Activity activity, int requestCode) {
        requestPermission(activity, getPermission(requestCode), requestCode);
    }

    public static void requestPermission(final Activity activity, String[] permissions, final int requestCode) {
        if (activity == null || permissions == null) {
            return;
        }
        ArrayList<String> needRequest = new ArrayList<String>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String permission : permissions) {
                if (activity.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                    needRequest.add(permission);
                }
            }
        }
        if (needRequest.size() < 1) {
            return;
        }
        ArrayList<String> needTipPermission = new ArrayList<String>();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] temp = new String[permissions.length];
            final String[] needRequestPermissions = needRequest.toArray(temp);
            for (String permission : needRequest) {
                if (activity.shouldShowRequestPermissionRationale(permission)) {
                    needTipPermission.add(permission);
                }
            }
            if (needTipPermission.size() > 0) {
                final Dialog dialog = DialogFactory.getOneBtnDialog(activity, generateTip(activity, needTipPermission.toArray(permissions)));
                dialog.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        activity.requestPermissions(needRequestPermissions,requestCode);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }else {
                activity.requestPermissions(needRequestPermissions, requestCode);
            }

        }
    }

    private static String generateTip(Context context, String[] permissions) {
        if (context == null || permissions == null || permissions.length < 1) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        boolean isSet = false;
        for (String permission : permissions) {
            if (Manifest.permission.READ_EXTERNAL_STORAGE.equals(permission) ||
                    Manifest.permission.WRITE_EXTERNAL_STORAGE.equals(permission)) {
                if (isSet) {
                    continue;
                }
                isSet = true;
                sb.append(context.getResources().getString(R.string.permission_tip_read_and_write_external_storage)).append("\n");
            }else if (Manifest.permission.INTERNET.equals(permission)) {
                sb.append(context.getResources().getString(R.string.permission_tip_internet)).append("\n");
            }
        }
        return sb.toString();
    }

    private static String[] getPermission(int requestCode) {
        if (requestCode == MiloConstants.RESULT_TYPE_PERMISSION_BASE) {
            return READ_AND_WRITE_EXTERNAL_STORAGE;
        }
        return READ_AND_WRITE_EXTERNAL_STORAGE;
    }

    public static boolean checkPermission(Activity activity,int requestCode) {
        return checkPermission(activity, getPermission(requestCode));
    }

    public static boolean checkPermission(Activity activity,String permission) {
        if (permission == null) {
            return false;
        }
        return checkPermission(activity, new String[]{permission});
    }

    public static boolean checkPermission(Activity activity, String[] permissions) {
        if (activity == null || permissions == null) {
            return false;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String permission : permissions) {
                if (activity.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

}
