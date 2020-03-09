package com.example.template.mvp.model.login;

import com.example.template.mvp.base.BaseBean;
import com.example.template.mvp.base.BaseView;
import com.example.template.mvp.bean.User;

/**
 * 功能描述：
 * Created by gfq on 2020/3/9.
 */
public interface ILoginView extends BaseView {

    /**
     * 显示登陆成功
     *
     * @param successMessage 成功信息
     */
    void showLoginSuccess(String successMessage);

    /**
     * 显示登陆失败
     *
     * @param errorMessage 失败信息
     */
    void showLoginFailed(String errorMessage);

    /**
     * 登陆成功
     *
     * @param user
     */
    void doSuccess(BaseBean<User> user);
}
