package com.example.template.mvp.model;

import com.example.template.mvp.contract.IPoetryContract;
import com.example.template.mvp.entity.PoetryEntity;
import com.example.template.mvp.iApiService.GetPoetryEntity;
import com.example.template.mvp.util.RetrofitManager;

import io.reactivex.Observable;

/**
 * 功能描述：
 * Created by gfq on 2020/3/9.
 */
public class PoetryModel implements IPoetryContract.IPoetryModel {


    private PoetryModel() {

    }

    public static PoetryModel getInstance() {
        return Inner.instance;
    }

    private static class Inner {
        private static final PoetryModel instance = new PoetryModel();
    }

    /**
     * 获取古诗词
     *
     * @return 古诗词
     */
    @Override
    public Observable<PoetryEntity> getPoetry() {
        return RetrofitManager.getInstance().createRs(GetPoetryEntity.class).getPoetry();
    }
}

