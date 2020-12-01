package com.example.appkotlin.util

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

/**
 *
 * 功能描述：
 * Created by gfq on 2020/4/22
 *
 **/

fun topLevelFunction() {
    val array: Array<String> = arrayOf("1", "2", "3")
    print(array[1])

    //定义一个可变的空集合
    val strList2 = mutableListOf<String>()

    var strList: List<String> = listOf("1", "2", "3")
    var anysList: List<Any> = strList

    //定义一个可变的空Set
    val strSet2 = mutableSetOf<String>()
    var strSet = setOf("1", "2", "3")

    //定义一个可变空Map
    val strMap2 = mutableMapOf<String, Int>()
    var strMap = mapOf("1" to 1, "2" to 2, "3" to 3)

}

fun showActivity(activity: AppCompatActivity, clz: Class<*>) {
    val intent = Intent(activity, clz)
    activity.startActivity(intent)
}