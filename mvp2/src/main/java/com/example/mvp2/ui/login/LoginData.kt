package com.example.mvp2.ui.login

import com.example.mvp2.http.request.base.BaseBean


/**
 * 功能描述：
 * Created by gfq on 2020/1/19.
 */

data class LoginData(
        val chapterTops: MutableList<String>,
        val collectIds: MutableList<String>
) : BaseBean()