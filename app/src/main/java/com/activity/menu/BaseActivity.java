package com.activity.menu;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class BaseActivity extends FragmentActivity {
    private static PermissionListerner permissionListerner;
    private static final int PERMISSION_REQUEST_CODE = 1;
    public static void requestPermissions(String[] permissions, PermissionListerner listerner) {
        Activity topActivity = ActivityManager.getTopActivity();
        if (topActivity==null)
            return;
        permissionListerner = listerner;
        //定义一个权限list
        List<String> permissionLists = new ArrayList<>();
        for (String permission : permissions) {
            //判断所申请的权限是不是已经通过，没通过返回false,通过返回true，则提示出来并拨打电话
            if (ContextCompat.checkSelfPermission(topActivity, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionLists.add(permission);
            }
        }

        if (!permissionLists.isEmpty()) {
            //申请权限回调函数
            ActivityCompat.requestPermissions(topActivity, permissionLists.toArray(new String[permissionLists.size()]), PERMISSION_REQUEST_CODE);
        } else {
            Toast.makeText(topActivity, "权限已全部被申请通过咯！", Toast.LENGTH_SHORT).show();
            permissionListerner.onGranted();
            //            call();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length>0){
                    List<String> denidPermissionList = new ArrayList<>();
                    for (int i=0;i<grantResults.length;i++){
                        int grandResult = grantResults[i];
                        String permission = permissions[i];
                        if (grandResult!= PackageManager.PERMISSION_GRANTED){
                            denidPermissionList.add(permission);
                        }
                    }
                    if (denidPermissionList.isEmpty()){
                        permissionListerner.onGranted();
                    }else{
                        permissionListerner.onDenid(denidPermissionList);
                    }
                }

                break;
            default:
                break;
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ActivityManager.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.removeActivity(this);
    }
}
