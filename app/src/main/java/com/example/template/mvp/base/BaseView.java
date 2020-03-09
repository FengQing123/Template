package com.example.template.mvp.base;

/**
 * 功能描述：说明了每一个View基本需要的一些操作
 * Created by gfq on 2020/3/9.
 */
public interface BaseView {

    void showLoading();

    void hideLoading();

    void onErrorCode(BaseBean bean);

}
