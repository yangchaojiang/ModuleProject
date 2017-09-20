package com.loginmodule;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.util.List;

/**
 * Created by yangc on 2017/4/5.
 * E-Mail:yangchaojiang@outlook.com
 * Deprecated: 默认加载页
 */
public abstract class AbstractLoadActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_PERMISSION_MULTI = 101;
    private static final int REQUEST_CODE_SETTING = 300;

 ///   public String[] pre = { Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_PHONE_STATE};
 private volatile String[] pre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(viewLayout());
        pre=permission();
        cameraTask();

    }

    /****
     * 已经获取权限可以开始业务
     ***/
    protected abstract void startApp();

    /***
     * 重写该方法赋予想要权限
     ***/
    protected  abstract String[] permission();
    /***
     * 布局id
     ***/
    protected abstract int viewLayout();
    /****
     * 权限适配
     ***/
    public void cameraTask() {
        if (Build.VERSION.SDK_INT >= 23) {
            requestPermission();
        } else {
            //实现业务处理
            startApp();
        }
    }


//----------------------------------权限回调处理----------------------------------//
    @Override
    protected void onActivityResult(int requestCode2, int resultCode, Intent data) {
        switch (requestCode2) {
            case REQUEST_CODE_SETTING: {
                requestPermission();
                break;
            }
        }
    }

    /**
     * 请求权限
     ***/
    private void requestPermission() {
        AndPermission.with(this)
                .requestCode(REQUEST_CODE_PERMISSION_MULTI)
                .permission(pre)
                .callback(permissionListener)
                // rationale作用是：用户拒绝一次权限，再次申请时先征求用户同意，再打开授权对话框，避免用户勾选不再提示。
                .rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                        AndPermission.rationaleDialog(AbstractLoadActivity.this, rationale).show();
                    }
                })
                .start();
    }
    /**
     * 回调监听。
     */
    private PermissionListener permissionListener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
          startApp();
        }

        @Override
        public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
            hasAlwaysDeniedPermission(requestCode,deniedPermissions);
        }
    };


    private  void  hasAlwaysDeniedPermission(int requestCode,@NonNull List<String> deniedPermissions){
        // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
        if (!AndPermission.hasAlwaysDeniedPermission(AbstractLoadActivity.this, deniedPermissions)&&REQUEST_CODE_PERMISSION_MULTI==requestCode) {
            // 第一种：用默认的提示语。
            AndPermission.defaultSettingDialog(AbstractLoadActivity.this, REQUEST_CODE_SETTING)
                    .setTitle(R.string.title_dialog)
                    .setMessage(R.string.rationale_ask_again)
                    .setPositiveButton(R.string.title_settings_dialog)
                    .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .show();
            // 更多自定dialog，请看上面。
        } else {
            // 用户否不勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
            requestPermission();
        }
    }
}
