//package com.example.template.mvp.base;
//
///**
// * 功能描述： 继承自BaseActivity,它是MVP活动的基类，封装好了Presenter的相关操作
// * Created by gfq on 2020/3/9.
// */
//public abstract class BaseMvpActivity<V extends BaseView, P extends BasePresenter> extends BaseActivity {
//
//    private P presenter;
//
//    /**
//     * 初始化presenter
//     */
//    @Override
//    protected void initPresenter() {
//        presenter = createPresenter();
//        if (presenter != null) {
//            presenter.attachView((V) this);
//        }
//    }
//
//    /**
//     * 创建presenter
//     *
//     * @return Presenter
//     */
//    protected abstract P createPresenter();
//
//
//    /**
//     * 得到presenter
//     *
//     * @return presenter
//     */
//    protected P getPresenter() {
//        return presenter;
//    }
//
//    /**
//     * 销毁
//     */
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if (presenter != null) {
//            presenter.detachView();
//        }
//    }
//}
//
//
