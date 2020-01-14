package com.example.mvp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvp2.course.activity.CourseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mPresenter = MainPresenter(this)

        btn_to_course.setOnClickListener {
            startActivity(Intent(this, CourseActivity::class.java))
        }

        btn_get_data.setOnClickListener {
            mPresenter.loadData()
        }
    }

    /**
     * 实现MainContract.View接口，并实现对应的方法
     */
    override fun onDataLoaded(s: String) {
        tv_data.text = s
    }
}
