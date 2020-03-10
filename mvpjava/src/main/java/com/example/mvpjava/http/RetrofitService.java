package com.example.mvpjava.http;

import com.example.mvpjava.http.cookie.CookiesManager;
import com.example.mvpjava.http.gson.BaseConverterFactory;
import com.example.mvpjava.util.YUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * 功能描述：
 * Created by gfq on 2020/3/9.
 */
public class RetrofitService {
    private static RetrofitService apiRetrofit;
    private API.WAZApi apiServer;

    //单例调用
    public static RetrofitService getInstance() {
        if (apiRetrofit == null) {
            synchronized (Object.class) {
                if (apiRetrofit == null) {
                    apiRetrofit = new RetrofitService();
                }
            }
        }
        return apiRetrofit;
    }

    //获取api对象
    public API.WAZApi getApiService() {
        return apiServer;
    }

    //初始化retrofit
    private RetrofitService() {

        //配置okhttp并设置时间、日志信息和cookies
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                //设置超时时间
                .connectTimeout(15, TimeUnit.SECONDS)
                //设置Cookie持久化
                .cookieJar(new CookiesManager(YUtils.getApplication()))
                .build();

        //关联okhttp并加上rxjava和gson的配置和baseurl
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(BaseConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(API.BASE_URL)
                .build();

        apiServer = retrofit.create(API.WAZApi.class);
    }

}
