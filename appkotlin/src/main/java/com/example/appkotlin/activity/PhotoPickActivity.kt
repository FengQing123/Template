package com.example.appkotlin.activity

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import com.example.appkotlin.R
import com.example.appkotlin.base.BaseActivity
import com.zhihu.matisse.Matisse
import com.zhihu.matisse.MimeType
import com.zhihu.matisse.engine.impl.GlideEngine
import com.zhihu.matisse.internal.entity.CaptureStrategy


/**
 *
 * 功能描述：
 * Created by gfq on 2020/12/2
 *
 **/

class PhotoPickActivity : BaseActivity() {

    private val REQUEST_CODE_CHOOSE = 1

    override fun getLayoutId(): Int {
        return R.layout.activity_photo_pick
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Matisse.from(this)
                //选择图片
                .choose(MimeType.ofImage())
                //是否只显示选择的类型的缩略图，就不会把所有图片视频都放在一起，而是需要什么展示什么
                .showSingleMediaType(true)
                //这两行要连用 是否在选择图片中展示照相 和适配安卓7.0 FileProvider
                .capture(true)
                .captureStrategy(CaptureStrategy(true, packageName + ".fileprovider"))
                //有序选择图片 123456...
                .countable(true)
                //最大选择数量
                .maxSelectable(1)
                //选择方向
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                //界面中缩略图的质量
                .thumbnailScale(0.85f)
                //Glide加载方式
                .imageEngine(GlideEngine())
                //设置是否预览，默认true
                .showPreview(false)
                //请求码
                .forResult(REQUEST_CODE_CHOOSE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            var mSelected = Matisse.obtainResult(data)
            Log.d("Matisse", "mSelected: $mSelected")
        }
    }
}