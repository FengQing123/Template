package com.example.template.mvp.iApiService;

import com.example.template.mvp.entity.PoetryEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * 功能描述：
 * Created by gfq on 2020/3/9.
 */
public interface GetPoetryEntity {
    /**
     * 获取古诗词
     *
     * @return 古诗词
     */
    @GET("all.json")
    Observable<PoetryEntity> getPoetry();
}
