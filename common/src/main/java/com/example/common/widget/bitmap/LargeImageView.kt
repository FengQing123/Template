package com.example.common.widget.bitmap

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import java.io.IOException
import java.io.InputStream


/**
 * 功能描述：
 * Created by gfq on 2020/5/27.
 */

class LargeImageView(context: Context, attrs: AttributeSet) : AppCompatImageView(context, attrs) {

    /**
     * 图片的宽度和高度
     */
    var mImageWidth = 0
    var mImageHeight = 0

    /**
     * 绘制的区域
     */

    @Volatile
    private var mRect = Rect()

    lateinit var mDecoder: BitmapRegionDecoder

    lateinit var mDetector: MoveGestureDetector

    companion object {
        private val options = BitmapFactory.Options()

        init {
            options.inPreferredConfig = Bitmap.Config.RGB_565
        }
    }

    init {
        init()
    }

    private fun init() {
        mDetector =
                MoveGestureDetector(context, object : MoveGestureDetector.SimpleMoveGestureDetector() {
                    override fun onMove(detector: MoveGestureDetector?): Boolean {
                        val moveX = detector?.moveX?.toInt()
                        val moveY = detector?.moveY?.toInt()

                        if (mImageWidth > width) {
                            mRect.offset(-moveX!!, 0)
                            checkWidth()
                            invalidate()
                        }

                        if (mImageHeight > height) {
                            mRect.offset(0, -moveY!!)
                            checkHeight()
                            invalidate()
                        }

                        return true
                    }
                })
    }

    fun setInputStream(inputStream: InputStream) {
        try {
            mDecoder = BitmapRegionDecoder.newInstance(inputStream, false)
            val tmpOptions = BitmapFactory.Options()
            // Grab the bounds for the scene dimensions
            tmpOptions.inJustDecodeBounds = true
            BitmapFactory.decodeStream(inputStream, null, tmpOptions)
            mImageWidth = tmpOptions.outWidth
            mImageHeight = tmpOptions.outHeight
            requestLayout()
            invalidate()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                inputStream.close()
            } catch (e: Exception) {
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = measuredWidth
        val height = measuredHeight

        val imageWidth = mImageWidth
        val imageHeight = mImageHeight

        //默认直接显示图片的中心区域，可以自己去调节
        mRect.left = imageWidth / 2 - width / 2
        mRect.top = imageHeight / 2 - height / 2
        mRect.right = mRect.left + width
        mRect.bottom = mRect.top + height
    }

    override fun onDraw(canvas: Canvas?) {
        val bm = mDecoder.decodeRegion(mRect, options)
        canvas?.drawBitmap(bm, 0f, 0f, null)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (MotionEvent.ACTION_MASK and event.action) {
            MotionEvent.ACTION_DOWN -> Log.e("hhh", "down")
            MotionEvent.ACTION_POINTER_DOWN -> Log.e("hhh", "down-pointer")
            MotionEvent.ACTION_MOVE -> Log.e("hhh", "move")
            MotionEvent.ACTION_UP -> Log.e("hhh", "up")
            MotionEvent.ACTION_POINTER_UP -> Log.e("hhh", "up-pointer")
        }

        mDetector.onTouchEvent(event)
        return true
    }

    private fun checkWidth() {
        val rect = mRect
        val imageWidth = mImageWidth
        val imageHeight = mImageHeight
        if (rect.right > imageWidth) {
            rect.right = imageWidth
            rect.left = imageWidth - width
        }
        if (rect.left < 0) {
            rect.left = 0
            rect.right = width
        }
    }

    private fun checkHeight() {
        val rect = mRect
        val imageWidth = mImageWidth
        val imageHeight = mImageHeight
        if (rect.bottom > imageHeight) {
            rect.bottom = imageHeight
            rect.top = imageHeight - height
        }
        if (rect.top < 0) {
            rect.top = 0
            rect.bottom = height
        }
    }

}