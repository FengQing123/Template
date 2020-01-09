package com.example.template.bean;

import java.util.List;

/**
 * 功能描述：
 * Created by gfq on 2020/1/9.
 */
public class DateCalendarBean {

    /**
     * 时间戳
     */
    private long[] millisArray;

    /**
     * 日期：01号
     */
    private String[] dayNumArray;

    public long[] getMillisArray() {
        return millisArray;
    }

    public void setMillisArray(long[] millisArray) {
        this.millisArray = millisArray;
    }

    public String[] getDayNumArray() {
        return dayNumArray;
    }

    public void setDayNumArray(String[] dayNumArray) {
        this.dayNumArray = dayNumArray;
    }
}
