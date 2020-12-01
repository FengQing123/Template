package com.example.appkotlin

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appkotlin.activity.PhotoViewActivity
import com.example.appkotlin.adapter.MenuAdapter
import com.example.appkotlin.base.BaseActivity
import com.example.appkotlin.bean.HomeMenuBean
import com.example.appkotlin.util.showActivity
import com.example.appkotlin.util.topLevelFunction
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    private val mList = mutableListOf<HomeMenuBean>()

    init {
        mList.add(HomeMenuBean("PhotoView的使用", PhotoViewActivity::class.java))
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recycleview.layoutManager = LinearLayoutManager(this)
        val adapter = MenuAdapter(mList)
        recycleview.adapter = adapter
        adapter.setOnItemClickListener { adapter, view, position ->
            val bean = mList[position]
            showActivity(mActivity, bean.clz)
        }

        topLevelFunction()
    }
}
