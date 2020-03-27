package com.example.template.jetpack.livedata;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述：
 * Created by gfq on 2020/3/27.
 */
public class LiveDataBus {

    private Map<String, MutableLiveData<Object>> mDataBusMap;

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
    public synchronized <T> MutableLiveData<T> with(String key, Class<T> type) {
        if (!mDataBusMap.containsKey(key)) {
            mDataBusMap.put(key, new MutableLiveData<>());
        }
        return (MutableLiveData<T>) mDataBusMap.get(key);
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
         * @param observer
         * @throws Exception
         */
        private void hook(Observer<? super T> observer) throws Exception {

        }
    }
}
