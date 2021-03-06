package com.example.template.mvp.base;

import com.example.template.mvp.http.API;
import com.example.template.mvp.http.RetrofitService;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 功能描述：Presenter的基类，
 * CompositeDisposable主要用途是及时取消订阅，以防止内存泄漏，
 * 具体CompositeDisposable的用法可参照
 * https://www.jianshu.com/p/2a882604bbe8
 * Created by gfq on 2020/3/9.
 */
public abstract class BasePresenter<V extends BaseView> {
    private CompositeDisposable compositeDisposable;
    public V baseView;

    /**
     * 这个后面可以直接用   Example：apiServer.login(username, password)；
     */
    protected API.WAZApi apiServer = RetrofitService.getInstance().getApiService();

    public BasePresenter(V baseView) {
        this.baseView = baseView;
    }

    /**
     * 解除绑定
     */
    public void detachView() {
        baseView = null;
        removeDisposable();
    }

    /**
     * 返回 view
     */
    public V getBaseView() {
        return baseView;
    }

    public void addDisposable(Observable<?> observable, BaseObserver observer) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer));
    }

    private void removeDisposable() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }
}