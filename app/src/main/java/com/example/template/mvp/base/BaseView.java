package com.example.template.mvp.base;

/**
 * 功能描述：说明了每一个View基本需要的一些操作
 * Created by gfq on 2020/3/9.
 */
public interface BaseView {

    /**
     * 显示进度框
     */
    void showProgressDialog();


    /**
     * 关闭进度框
     */
    void hideProgressDialog();


    /**
     * 出错信息的回调
     *
     * @param result 错误信息
     */
    void onError(String result);

}
