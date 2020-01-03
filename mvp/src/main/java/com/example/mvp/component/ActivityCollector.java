package com.example.mvp.component;

import android.app.Activity;

import java.util.HashSet;
import java.util.Set;

/**
 * 功能描述：一键退出APP
 * Created by gfq on 2020/1/3.
 */
public class ActivityCollector {

    private static ActivityCollector activityCollector;

    public synchronized static ActivityCollector getInstance() {
        if (activityCollector == null) {
            synchronized (ActivityCollector.class) {
                if (activityCollector == null) {
                    activityCollector = new ActivityCollector();
                }
            }
        }
        return activityCollector;
    }

    /**
     * Set集合和List类似，但是无法存储相同的元素
     */
    private Set<Activity> allActivities;

    public void addActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(act);
    }

    public void removeActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }

    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (Activity act : allActivities) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}
