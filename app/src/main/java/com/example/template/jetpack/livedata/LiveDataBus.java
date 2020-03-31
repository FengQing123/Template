package com.example.template.jetpack.livedata;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述：
 * Created by gfq on 2020/3/27.
 */
public class LiveDataBus {

    private Map<String, BusMutableLiveData<Object>> mDataBusMap;

    private static LiveDataBus mLiveDataBus = new LiveDataBus();

    private LiveDataBus() {
        mDataBusMap = new HashMap<>();
    }

    public static LiveDataBus getInstance() {
        return mLiveDataBus;
    }

    /**
     * 给外部调用的方法，用来注册订阅者
     *
     * @param key
     * @param type
     * @param <T>
     * @return
     */
    public synchronized <T> BusMutableLiveData<T> with(String key, Class<T> type) {
        if (!mDataBusMap.containsKey(key)) {
            mDataBusMap.put(key, new BusMutableLiveData<>());
        }
        return (BusMutableLiveData<T>) mDataBusMap.get(key);
    }

    public static class BusMutableLiveData<T> extends MutableLiveData<T> {
        @Override
        public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
            super.observe(owner, observer);
            try {
                hook(observer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * hook方法，改变LiveData的参数把mVersion赋值给observer.mLastVersion，使observer.mLastVersion == mVersion
         *
         * @param observer 这里参数使用 (? super T) ,防止别人修改参数 （这里还要学习下泛型）
         * @throws Exception
         */
        private void hook(Observer<? super T> observer) throws Exception {
            Class<LiveData> liveDataClass = LiveData.class;
            //获取mObservers属性
            Field mObserversField = liveDataClass.getDeclaredField("mObservers");
            mObserversField.setAccessible(true);
            //获取到这个成员变量的对象
            Object mObserversObject = mObserversField.get(this);
            //获取map对应的Class类（为了调用get方法）
            Class<?> mObserversClass = mObserversObject.getClass();
            //获取get(K k) 方法,并执行get方法
            Method get = mObserversClass.getDeclaredMethod("get", Object.class);
            get.setAccessible(true);
            Object invokeEntry = get.invoke(mObserversObject, observer);

            Object observerWrapper = null;

            if (invokeEntry != null && invokeEntry instanceof Map.Entry) {
                observerWrapper = ((Map.Entry) invokeEntry).getValue();
            }

            if (observerWrapper == null) {
                throw new NullPointerException("observerWrapper is null");
            }

            //得到observerWrapper的父类，编译擦除
            Class<?> superClass = observerWrapper.getClass().getSuperclass();
            Field mLastVersion = superClass.getDeclaredField("mLastVersion");
            mLastVersion.setAccessible(true);

            //得到mVersion
            Field mVersion = liveDataClass.getDeclaredField("mVersion");
            mVersion.setAccessible(true);

            //把mVersion 填到 mLastVersion
            Object mVersionObject = mVersion.get(this);
            mLastVersion.set(observerWrapper, mVersionObject);

        }
    }
}
