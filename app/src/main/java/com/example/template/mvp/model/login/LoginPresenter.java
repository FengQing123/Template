package com.example.template.mvp.model.login;

import com.example.template.mvp.base.BaseBean;
import com.example.template.mvp.base.BaseObserver;
import com.example.template.mvp.base.BasePresenter;
import com.example.template.mvp.bean.User;

/**
 * 功能描述：
 * Created by gfq on 2020/3/9.
 */
public class LoginPresenter extends BasePresenter<ILoginView> {

    LoginPresenter(ILoginView baseView) {
        super(baseView);
    }

    void login(String username, String password) {
        addDisposable(apiServer.login(username, password), new BaseObserver<BaseBean<User>>(baseView, true) {
            @Override
            public void onSuccess(BaseBean<User> bean) {
                baseView.showLoginSuccess("登录成功（￣▽￣）");
                baseView.doSuccess(bean);
            }

            @Override
            public void onError(String msg) {
                baseView.showLoginFailed(msg + "(°∀°)ﾉ");
            }
        });
    }
}
