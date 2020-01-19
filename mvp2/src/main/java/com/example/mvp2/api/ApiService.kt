package com.example.mvp2.api

import com.example.mvp2.ui.login.LoginBean
import com.example.mvp2.ui.login.LoginData
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*


/**
 * 功能描述：
 * Created by gfq on 2020/1/15.
 */

interface ApiService {

    /**
     * 登录
     * 使用Retrofit实现
     * http://www.wanandroid.com/user/login
     * @param username
     * @param password
     */
    @POST("user/login")
    @FormUrlEncoded
    fun loginWanAndroid(@Field("username") username: String,
                        @Field("password") password: String): Call<LoginBean>

    /**
     * 登录
     * 配合RxJava
     */
    @POST("user/login")
    @FormUrlEncoded
    fun rxLoginWanAndroid(@Field("username") username: String,
                          @Field("password") password: String): Observable<LoginBean>

}