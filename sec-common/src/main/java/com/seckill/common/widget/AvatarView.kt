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

    private val WIDTH: Float = ConvertUtils.dp2px(200F)
    private val PADDING: Float = ConvertUtils.dp2px(50F)
    private val EDGE_WIDTH: Float = ConvertUtils.dp2px(5F)

    // 反锯齿，一般都要设置，否则会有毛边
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    // 图形叠加效果
    private var xfermode: Xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    private var bitmap: Bitmap
    private var savedArea = RectF()

    init {
        bitmap = getAvatar(WIDTH.toInt())
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        savedArea.set(PADDING, PADDING, PADDING + WIDTH, PADDING + WIDTH)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            canvas.drawOval(PADDING, PADDING, PADDING + WIDTH, PADDING + WIDTH, paint)
            // 离屏缓冲
            val saved = canvas.saveLayer(savedArea, paint)
            canvas.drawOval(
                PADDING + EDGE_WIDTH,
                PADDING + EDGE_WIDTH,
                PADDING + WIDTH - EDGE_WIDTH,
                PADDING + WIDTH - EDGE_WIDTH,
                paint
            )
            paint.xfermode = xfermode
            canvas.drawBitmap(bitmap, PADDING, PADDING, paint)
            paint.xfermode = null
            canvas.restoreToCount(saved)
        }
    }

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