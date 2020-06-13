package com.seckill.common.utilities

import android.content.res.Resources
import android.util.TypedValue

/**
 * 转换相关工具类
 */
object ConvertUtils {

    fun dp2px(dpValue: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dpValue, Resources.getSystem().displayMetrics
        )
    }
}