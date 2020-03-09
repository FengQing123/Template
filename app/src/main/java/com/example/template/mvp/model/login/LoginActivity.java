package com.example.template.mvp.model.login;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.template.R;
import com.example.template.module.recycleview.RecycleViewTestActivity;
import com.example.template.mvp.base.BaseActivity;
import com.example.template.mvp.base.BaseBean;
import com.example.template.mvp.bean.User;
import com.example.template.mvp.common.GlobalConstant;
import com.example.template.mvp.util.SpUtil;
import com.example.template.mvp.util.ToastUtil;
import com.example.template.mvp.util.YUtils;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 功能描述：
 * Created by gfq on 2020/3/9.
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
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.btn_register)
    Button mBtnRegister;

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
    protected void initData() {
    }

    @Override
    protected void initView() {
        LoginTextWatcher textWatcher = new LoginTextWatcher(mTilUsername, mTilPassword);
        mEtUsername.addTextChangedListener(textWatcher);
        mEtPassword.addTextChangedListener(textWatcher);
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
    public void doSuccess(BaseBean<User> user) {
        //存进sp里面
        SpUtil.setBoolean(GlobalConstant.IS_LOGIN, true);
        SpUtil.setString(GlobalConstant.USERNAME, user.data.username);
        SpUtil.setString(GlobalConstant.PASSWORD, user.data.password);
        startActivity(RecycleViewTestActivity.class, true);
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
                startActivity(RecycleViewTestActivity.class, false);
                break;
            default:
                break;
        }
    }
}
