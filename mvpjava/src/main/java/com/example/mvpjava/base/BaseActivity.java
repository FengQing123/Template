package com.example.mvpjava.base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mvpjava.util.YUtils;

import butterknife.ButterKnife;

/**
 * 功能描述： Activity的基类
 * Created by gfq on 2020/3/9.
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView {
    protected P presenter;

    protected abstract P createPresenter();

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(LayoutInflater.from(this).inflate(getLayoutId(), null));
        ButterKnife.bind(this);
        presenter = createPresenter();
        initView();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //销毁时，解除绑定
        if (presenter != null) {
            presenter.detachView();
        }
    }

    protected void initListener() {
    }

    @Override
    public void showLoading() {
        YUtils.showLoading(this, "加载中");
    }

    @Override
    public void hideLoading() {
        YUtils.hideLoading();
    }

    /**
     * 可以处理异常
     */
    @Override
    public void onErrorCode(BaseBean bean) {
    }

    /**
     * 启动activity
     *
     * @param activity 当前活动
     * @param isFinish 是否结束当前活动
     */
    public void startActivity(Class<?> activity, boolean isFinish) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
        if (isFinish) {
            finish();
        }
    }
}
