package com.example.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 功能描述：
 * Created by gfq on 2020/1/9.
 */
public class DateUtil {


    public static Calendar calendar() {
        Calendar cal = GregorianCalendar.getInstance(Locale.CHINESE);
//        cal.setFirstDayOfWeek(Calendar.MONDAY);
        return cal;
    }

    /**
     * 获得当前时间的毫秒数
     * <p>
     * 详见{@link System#currentTimeMillis()}
     *
     * @return
     */
    public static long millis() {
        return System.currentTimeMillis();
    }

    /**
     * 今天是星期的第几天
     *
     * @return 星期四 返回 5
     */
    public static int dayOfWeek() {
        return calendar().get(Calendar.DAY_OF_WEEK);
    }


    /**
     * 获取几号
     *
     * @param time
     * @return
     */
    public static String getDayNum(long time) {
        return new SimpleDateFormat("dd", Locale.CHINA).format(time);
    }

}
