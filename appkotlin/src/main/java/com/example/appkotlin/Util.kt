package com.example.appkotlin

/**
 *
 * 功能描述：
 * Created by gfq on 2020/4/22
 *
 **/

fun topLevelFunction() {
    val array: Array<String> = arrayOf("1", "2", "3")
    print(array[1])

    var strList: List<String> = listOf("1", "2", "3")
    var anysList: List<Any> = strList

    var strSet = setOf("1", "2", "3")

    var strMap = mapOf("1" to 1, "2" to 2, "3" to 3)

}