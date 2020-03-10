package com.example.mvpjava.model.login;

import com.example.mvpjava.base.BaseBean;
import com.example.mvpjava.base.BaseObserver;
import com.example.mvpjava.base.BasePresenter;

/**
 * 功能描述：
 * Created by gfq on 2020/3/10.
 */
public class LoginPresenter extends BasePresenter<ILoginView> {

    public LoginPresenter(ILoginView baseView) {
        super(baseView);
    }

    void login(String username, String password) {
        addDisposable(apiServer.login(username, password), new BaseObserver<BaseBean<UserBean>>(baseView, true) {

            @Override
            public void onSuccess(BaseBean<UserBean> bean) {
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
