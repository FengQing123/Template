package com.example.mvp2.api

import com.example.mvp2.http.request.base.BaseResponse


/**
 * 功能描述：
 * Created by gfq on 2020/1/15.
 */

class WanResponse<E> : BaseResponse<E> {

    var errorCode: Int = 0

    var errorMsg: String = ""

    var data: E? = null

    override fun getCode(): Int = errorCode

    override fun getMsg(): String = errorMsg

    override fun getDatas(): E? {
        return data
    }

    override fun setCode(code: Int) {
        this.errorCode = code
    }

    override fun setMsg(msg: String) {
        this.errorMsg = msg
    }

    override fun setDatas(data: E?) {
        this.data = data
    }


}