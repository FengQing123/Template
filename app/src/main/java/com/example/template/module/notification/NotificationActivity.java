package com.example.template.module.notification;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationManagerCompat;

import com.example.template.R;
import com.example.template.app.BaseActivity;

/**
 * 功能描述：
 * Created by gfq on 2019/12/31.
 */
public class NotificationActivity extends BaseActivity {

    @Override
    protected int getLayout() {
        return R.layout.activity_notification;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!isNotificationServiceEnable()) {
            gotoNotificationAccessSetting();
        }

        Intent NLServiceIntent = new Intent(mActivity, NLService.class);
        startService(NLServiceIntent);
    }


    /**
     * 判断是否有监听通知信息权限
     *
     * @return true：有
     */
    private boolean isNotificationServiceEnable() {
        return NotificationManagerCompat.getEnabledListenerPackages(this).contains(getPackageName());
    }

    /**
     * 跳转到通知使用权限界面
     *
     * @return
     */
    private boolean gotoNotificationAccessSetting() {
        try {
            Intent intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return true;
        } catch (ActivityNotFoundException e) {//普通情况下找不到的时候需要再特殊处理找一次
            try {
                Intent intent = new Intent();
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ComponentName cn = new ComponentName("com.android.settings", "com.android.settings.Settings$NotificationAccessSettingsActivity");
                intent.setComponent(cn);
                intent.putExtra(":settings:show_fragment", "NotificationAccessSettings");
                startActivity(intent);
                return true;
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            Toast.makeText(this, "对不起，您的手机暂不支持", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            return false;
        }
    }

}
