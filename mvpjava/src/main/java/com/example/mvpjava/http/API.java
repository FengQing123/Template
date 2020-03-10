package com.example.mvpjava.http;

import com.example.mvpjava.base.BaseBean;
import com.example.mvpjava.model.login.UserBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * 功能描述：
 * Created by gfq on 2020/3/9.
 */
public class API {
    static final String BASE_URL = "https://www.wanandroid.com/";

    public interface WAZApi {

        //-----------------------【首页相关】----------------------


        //首页文章列表 这里的{}是填入页数
//        @GET("article/list/{page}/json")
//        Observable<BaseBean<Article>> getArticleList(@Path("page") Integer page);


        //-----------------------【登录注册】----------------------

        //登录
        @FormUrlEncoded
        @POST("user/login")
        Observable<BaseBean<UserBean>> login(@Field("username") String username, @Field("password") String password);

        //注册
        @FormUrlEncoded
        @POST("user/register")
        Observable<BaseBean<UserBean>> register(@Field("username") String username, @Field("password") String password, @Field("repassword") String repassword);


        //-----------------------【  收藏  】----------------------

        //收藏站内文章
        @POST("lg/collect/{id}/json")
        Observable<BaseBean> collectIn(@Path("id") Integer id);

        //取消收藏---文章列表
        @POST("lg/uncollect_originId/{id}/json")
        Observable<BaseBean> uncollect(@Path("id") Integer id);


    }
}
