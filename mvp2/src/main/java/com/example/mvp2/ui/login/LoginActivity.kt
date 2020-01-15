package com.example.mvp2.ui.login

import com.example.mvp2.R
import com.example.mvp2.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*


/**
 * 功能描述：
 * Created by gfq on 2020/1/15.
 */

class LoginActivity : BaseActivity<LoginView, LoginPresent>() {

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun initPresenter(): LoginPresent = LoginPresent()

    override fun initView() {
        btn_login.setOnClickListener {
            if (validate()) {
                mPresenter?.login(edit_name.text.trim().toString(), edit_password.text.trim().toString())
            }
        }
    }

    override fun initData() {

    }


    /**
     * 验证用户名和密码
     */
    private fun validate(): Boolean {
        var valid = true
        val name = edit_name.text.trim().toString()
        val password = edit_password.text.trim().toString()

        if (name.isEmpty()) {
            edit_name.error = getString(R.string.username_not_empty)
            valid = false
        }
        if (password.isEmpty()) {
            edit_password.error = getString(R.string.password_not_empty)
            valid = false
        }
        return valid
    }

}