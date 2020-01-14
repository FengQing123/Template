package com.example.mvp2


/**
 * 功能描述：Contract是个契约类，没有实现任何功能，
 * MainPresenter需要实现Presenter接口中的loadData()方法,来完成数据从服务器的拉取
 * MainActivity需要实现View接口中的onDataLoaded()方法，来展示数据
 * Created by gfq on 2020/1/14.
 */

interface MainContract {

    interface Presenter {
        fun loadData()
    }

    interface View {
        fun onDataLoaded(s: String)
    }
}