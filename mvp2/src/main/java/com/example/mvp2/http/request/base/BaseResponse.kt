package com.example.mvp2.http.request.base


/**
 * 功能描述：
 * Created by gfq on 2020/1/15.
 */

interface BaseResponse<E> {

    fun getCode(): Int

    fun getMsg(): String

    fun getDatas(): E?

    fun setCode(code: Int)

    fun setMsg(msg: String)

    fun setDatas(data: E?)
}