package com.seckill.common.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.seckill.common.R
import com.seckill.common.utilities.ConvertUtils

/**
 * 头像
 */
class AvatarView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    // Bitmap 宽度
    private val width: Float = ConvertUtils.dp2px(200F)

    private val padding: Float = ConvertUtils.dp2px(50F)

    private val edgeWidth: Float = ConvertUtils.dp2px(5F)

    // 抗锯齿标志，一般都要设置，否则会有毛边
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    // 图形叠加效果
    private var xfermode: Xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    private var bitmap: Bitmap
    private var savedArea = RectF()

    init {
        bitmap = getAvatar(width.toInt())
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        savedArea.set(padding, padding, padding + width, padding + width)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            canvas.drawOval(padding, padding, padding + width, padding + width, paint)
            // canvas.saveLayer() 把绘制区域拉到单独的离屏缓冲里
            val saved = canvas.saveLayer(savedArea, paint)
            // 绘制 A 图形
            canvas.drawOval(
                padding + edgeWidth,
                padding + edgeWidth,
                padding + width - edgeWidth,
                padding + width - edgeWidth,
                paint
            )
            // 设置 xfermode
            paint.xfermode = xfermode
            // 绘制 B 图形
            canvas.drawBitmap(bitmap, padding, padding, paint)
            // 恢复 xfermode
            paint.xfermode = null
            canvas.restoreToCount(saved)
        }
    }

    /**
     * 获取 Bitmap 对象
     */
    private fun getAvatar(width: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.ic_avatar_default, options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width
        return BitmapFactory.decodeResource(resources, R.drawable.ic_avatar_default, options)
    }
}