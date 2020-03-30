package com.example.template.jetpack.livedata;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * 功能描述：创建LiveData对象（MutableLiveData），要确保存储在ViewModel对象中
 * Created by gfq on 2020/3/27.
 */
public class NameViewModel extends ViewModel {

    private MutableLiveData<String> currentName;

    public int i;

    public MutableLiveData<String> getCurrentName() {
        if (currentName == null) {
            currentName = new MutableLiveData<String>();
        }
        return currentName;
    }
}
