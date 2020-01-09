package com.example.template.module;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.common.util.DateUtil;
import com.example.common.util.L;
import com.example.template.R;
import com.example.template.adapter.CalendarAdapter;
import com.example.template.app.BaseActivity;
import com.example.template.bean.DateCalendarBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述：
 * Created by gfq on 2020/1/9.
 */
public class CalendarActivity extends BaseActivity {

    private static final String TAG = "CalendarActivity";

    @Override
    protected int getLayout() {
        return R.layout.activity_calendar;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        long millis = DateUtil.millis();
        int dayOfWeek = DateUtil.dayOfWeek();

        L.e(TAG, "millis=" + millis + ",dayOfWeek=" + dayOfWeek);


        List<DateCalendarBean> list = new ArrayList<>();
        DateCalendarBean bean = new DateCalendarBean();

        long[] array = getWeekMillisArray();
        String[] dayNum = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            dayNum[i] = DateUtil.getDayNum(array[i]);
        }
        bean.setMillisArray(array);
        bean.setDayNumArray(dayNum);
        list.add(bean);

        RecyclerView mRecycleCalendar = findViewById(R.id.recycle_calendar);
        CalendarAdapter adapter = new CalendarAdapter(list);
        mRecycleCalendar.setLayoutManager(new LinearLayoutManager(mRecycleCalendar.getContext(), LinearLayoutManager.HORIZONTAL, false));
        mRecycleCalendar.setAdapter(adapter);


    }

    /**
     * 获取这一周的数组时间戳
     *
     * @return
     */
    public long[] getWeekMillisArray() {
        long dayMillis = 24 * 3600 * 1000;
        long nowMillis = DateUtil.millis();
        int nowDayOfWeek = DateUtil.dayOfWeek() - 1;

        long[] millisArray = new long[7];

        for (int i = 0; i < 7; i++) {
            if (i < nowDayOfWeek) {
                millisArray[i] = nowMillis - (nowDayOfWeek - i) * dayMillis;
            } else if (i > nowDayOfWeek) {
                millisArray[i] = nowMillis + (i - nowDayOfWeek) * dayMillis;
            } else {
                millisArray[i] = nowMillis;
            }
        }

        return millisArray;
    }


    /**
     * 获取当前周的上一周的时间戳集合
     *
     * @return
     */
    public long[] getLastWeekMillis(long[] array) throws Exception {
        if (array.length < 7) {
            throw new Exception("需要传入周时间戳数组");
        }
        long[] lastMillisArray = new long[7];
        long dayMillis = 24 * 3600 * 1000;
        for (int i = 0; i < 7; i++) {
            lastMillisArray[i] = array[i] - 7 * dayMillis;
        }
        return lastMillisArray;
    }

    /**
     * 获取当前周的下一周的时间戳集合
     *
     * @param array
     * @return
     */
    public long[] getNextWeekMillis(long[] array) throws Exception {
        if (array.length < 7) {
            throw new Exception("需要传入周时间戳数组");
        }
        long[] nextMillisArray = new long[7];
        long dayMillis = 24 * 3600 * 1000;
        for (int i = 0; i < 7; i++) {
            nextMillisArray[i] = array[i] + 7 * dayMillis;
        }
        return nextMillisArray;
    }


}
