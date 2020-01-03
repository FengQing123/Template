package com.example.mvp.core.db;

import com.example.mvp.core.dao.HistoryData;

import java.util.List;

/**
 * 功能描述：
 * Created by gfq on 2020/1/3.
 */
public interface DbHelper {
    /**
     * 增加历史数据
     *
     * @param data  added string
     * @return  List<HistoryData>
     */
    List<HistoryData> addHistoryData(String data);

    /**
     * Clear search history data
     */
    void clearHistoryData();

    /**
     * Load all history data
     *
     * @return List<HistoryData>
     */
    List<HistoryData> loadAllHistoryData();
}
