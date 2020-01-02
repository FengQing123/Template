package com.example.template.module.notification;

import android.content.Intent;
import android.os.Build;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

import androidx.annotation.RequiresApi;

import com.example.common.util.L;

/**
 * 功能描述：监听通知内容（需要通知使用权）
 * Created by gfq on 2019/12/31.
 */
@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
public class NLService extends NotificationListenerService {

    private static final String TAG = "NLService";

    @Override
    public void onCreate() {
        super.onCreate();
        L.e(TAG, "-----onCreate--------");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        L.e(TAG, "-----onStartCommand--------");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);
        L.e(TAG, "-----onNotificationPosted--------" + sbn.getPackageName());
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);
        L.e(TAG, "-----onNotificationRemoved--------" + sbn.getPackageName());
    }

}
