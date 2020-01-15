package com.example.mvp2.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


/**
 * 功能描述：androidx 包中 使用 setMaxLifecycle() 替代 setUserVisibleHint() 来实现懒加载
 * 实现Fragment懒加载的新方案：
 * 在构造FragmentPagerAdapter时传入BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT，
 * 将加载数据的逻辑放到Fragment的onResume()方法中即可
 * 参考文章：
 * https://blog.csdn.net/qq_36486247/article/details/102531304
 * Created by gfq on 2020/1/15.
 */

abstract class LazyFragment : Fragment() {

    private var mContext: Context? = null
    private var isFirstLoad = true // 是否第一次加载

    /**
     * 设置布局资源Id
     */
    protected abstract fun getLayoutId(): Int

    /**
     * 初始化控件
     */
    protected abstract fun initView(view: View)

    /**
     * 初始化数据
     */
    protected abstract fun initData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = activity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = LayoutInflater.from(mContext).inflate(getLayoutId(), container, false)
        initView(view)
        return view
    }

    override fun onResume() {
        super.onResume()
        if (isFirstLoad) {
            initData()
            isFirstLoad = false
        }
    }


}