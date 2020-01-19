package com.example.mvp2.ui.login

import com.example.common.util.L
import com.example.mvp2.api.ApiService
import com.example.mvp2.base.BasePresenter
import com.example.mvp2.constant.Constant
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * 功能描述：
 * Created by gfq on 2020/1/15.
 */

class LoginPresent : BasePresenter<LoginView>() {

    fun login(name: String, password: String) {

//        request(name, password)

        requestInRx(name, password)

    }

    /**
     * 使用Retrofit请求
     */
    private fun request(name: String, password: String) {
        val retrofit = Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val apiService = retrofit.create(ApiService::class.java)
        val call = apiService.loginWanAndroid(name, password)
        call.enqueue(object : Callback<LoginBean> {
            override
            fun onResponse(call: Call<LoginBean>, response: Response<LoginBean>) {
                // 处理返回数据
                if (response.isSuccessful()) {
                    L.e("TAG", "onResponse: " + response.body()?.errorMsg)
                }
            }

            override
            fun onFailure(call: Call<LoginBean>, t: Throwable) {
                L.e("TAG", "onFailure: 请求数据失败")
            }
        })
    }


    /**
     * 配合RxJava请求
     */
    private fun requestInRx(name: String, password: String) {
        val retrofit = Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        val apiService = retrofit.create(ApiService::class.java)
        val observable = apiService.rxLoginWanAndroid(name, password)
        observable.subscribeOn(Schedulers.io())//在io线程上执行请求
                .observeOn(AndroidSchedulers.mainThread())//返回结果在主线程上操作
                .subscribe(object : Observer<LoginBean> {
                    override fun onComplete() {
                        L.e("TAG", "onComplete")
                    }

                    override fun onSubscribe(d: Disposable) {
                        L.e("TAG", "onSubscribe")
                    }

                    override fun onNext(t: LoginBean) {
                        L.e("TAG", "onNext=" + t.errorMsg)
                    }

                    override fun onError(e: Throwable) {
                        L.e("TAG", "onError=" + e.message)
                    }
                })

    }
}