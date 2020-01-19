package com.example.mvp2.ui.login

/**
 * 功能描述：
 * Created by gfq on 2020/1/19.
 */
class LoginBean {

    /**
     * data : {"admin":false,"chapterTops":[],"collectIds":[],"email":"","icon":"","id":41026,"nickname":"feng_qing","password":"","publicName":"feng_qing","token":"","type":0,"username":"feng_qing"}
     * errorCode : 0
     * errorMsg :
     */

    var data: DataBean? = null
    var errorCode: Int = 0
    var errorMsg: String? = null

    class DataBean {
        /**
         * admin : false
         * chapterTops : []
         * collectIds : []
         * email :
         * icon :
         * id : 41026
         * nickname : feng_qing
         * password :
         * publicName : feng_qing
         * token :
         * type : 0
         * username : feng_qing
         */

        var isAdmin: Boolean = false
        var email: String? = null
        var icon: String? = null
        var id: Int = 0
        var nickname: String? = null
        var password: String? = null
        var publicName: String? = null
        var token: String? = null
        var type: Int = 0
        var username: String? = null
        var chapterTops: List<*>? = null
        var collectIds: List<*>? = null
    }
}
