package com.example.mvpjava.model.login;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.mvpjava.R;
import com.example.mvpjava.base.BaseActivity;
import com.example.mvpjava.base.BaseBean;
import com.example.mvpjava.common.GlobalConstant;
import com.example.mvpjava.util.SpUtil;
import com.example.mvpjava.util.ToastUtil;
import com.example.mvpjava.util.YUtils;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 功能描述：
 * Created by gfq on 2020/3/10.
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginView {

    @BindView(R.id.et_username)
    EditText mEtUsername;
    @BindView(R.id.til_username)
    TextInputLayout mTilUsername;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    @BindView(R.id.til_password)
    TextInputLayout mTilPassword;

    private String mUsername;
    private String mPassword;

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        LoginTextWatcher textWatcher = new LoginTextWatcher(mTilUsername, mTilPassword);
        mEtUsername.addTextChangedListener(textWatcher);
        mEtPassword.addTextChangedListener(textWatcher);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void showLoginSuccess(String successMessage) {
        ToastUtil.showToast(successMessage);
    }

    @Override
    public void showLoginFailed(String errorMessage) {
        ToastUtil.showToast(errorMessage);
    }

    @Override
    public void doSuccess(BaseBean<UserBean> user) {
        //存进sp里面
        SpUtil.setBoolean(GlobalConstant.IS_LOGIN, true);
        SpUtil.setString(GlobalConstant.USERNAME, user.data.username);
        SpUtil.setString(GlobalConstant.PASSWORD, user.data.password);
    }

    @OnClick({R.id.btn_login, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                YUtils.closeSoftKeyboard();
                if (isValid()) {
                    presenter.login(mUsername, mPassword);
                } else {
                    ToastUtil.showToast("填写错误 (°∀°)ﾉ");
                }
                break;
            case R.id.btn_register:
                YUtils.closeSoftKeyboard();
                break;
            default:
                break;
        }
    }

    /**
     * 判断账号和密码输入是否正确
     *
     * @return
     */
    private boolean isValid() {
        mUsername = mEtUsername.getText().toString().trim();
        mPassword = mEtPassword.getText().toString().trim();
        return check(mUsername, mTilUsername) && check(mPassword, mTilPassword);
    }

    /**
     * 判断输入是否正确
     *
     * @param string          输入的内容
     * @param textInputLayout textInputLayout控件
     * @return
     */
    private boolean check(String string, TextInputLayout textInputLayout) {
        return !TextUtils.isEmpty(string) && string.length() <= textInputLayout.getCounterMaxLength() && textInputLayout.getCounterMaxLength() / 2 <= string.length();
    }

}
