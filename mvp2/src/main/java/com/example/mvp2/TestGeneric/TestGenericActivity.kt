package com.example.mvp2.TestGeneric

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


/**
 * 功能描述：
 * Created by gfq on 2020/1/15.
 */

class TestGenericActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val box: Box<Int> = Box<Int>(1)

        val box1 = Box(1)
    }
}