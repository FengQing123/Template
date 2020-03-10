package com.example.mvpjava.util;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 功能描述：RxJava的工具类，执行线程调度工作
 * Created by gfq on 2020/3/9.
 */
public class RxJavaUtil {
    /**
     * 线程调度工作
     *
     * @param observable 被观察者
     * @param <T>        类型
     */
    public static <T> Observable toSubscribe(Observable<T> observable) {
        return observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
