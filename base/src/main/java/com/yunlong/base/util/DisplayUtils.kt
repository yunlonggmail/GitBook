package com.yunlong.base.util

import android.content.Context
import android.util.DisplayMetrics

/**
 * Created by shiyunlong on 2017/8/15.
 * 工具类
 */
object DisplayUtils {

    var mDisplayMetrics: DisplayMetrics? = null

    val ROUND_DIFFERENCE: Float = 0.5f
    /**
     * 初始化
     */
    fun init(context: Context?) {
        mDisplayMetrics = context?.resources?.displayMetrics
    }


    /**
     * dp 转 px

     * @param dp dp值
     * *
     * @return 转换后的像素值
     */
    fun dp2px(dp: Float): Float {
        return mDisplayMetrics?.density?.times(dp)?.plus(ROUND_DIFFERENCE) as Float
    }

    /**
     * dp转px
     * @param dp dp值
     * @return 转换后的像素值
     */
    fun dp2px(dp: Int): Int {
        return dp2px(dp.toFloat()).toInt()
    }

    /**
     * 获取屏幕高度
     */
    fun getScreenHeight(): Int {
        return mDisplayMetrics?.heightPixels as Int
    }

    /**
     * 获取屏幕宽度
     */
    fun getScreenWidth(): Int {
        return mDisplayMetrics?.widthPixels as Int
    }

}

