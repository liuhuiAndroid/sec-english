package com.seckill.common.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.seckill.common.utilities.ConvertUtils
import kotlin.math.cos
import kotlin.math.sin

/**
 * 仪表盘
 */
class DashboardView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    // 矩形角度
    private val angle = 120F

    // 矩形半径
    private val redius = 200F

    // 刻度个数
    private val count = 8

    // 指针长度
    private val length = ConvertUtils.dp2px(50F)

    // 反锯齿，一般都要设置，否则会有毛边
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val dash = Path()
    private lateinit var pathEffect: PathDashPathEffect

    init {
        paint.style = Paint.Style.STROKE
        // 设置粗细
        paint.strokeWidth = ConvertUtils.dp2px(2F)
        // 虚线
        dash.addRect(
            0F, 0F,
            ConvertUtils.dp2px(2F),
            ConvertUtils.dp2px(10F),
            Path.Direction.CW
        )
        // 计算间距
        val arcPath = Path().apply {
            addArc(
                width / 2 - redius,
                height / 2 - redius,
                width / 2 + redius,
                height / 2 + redius,
                90 + angle / 2, 360 - angle
            )
        }
        val pathMeasure = PathMeasure(arcPath, false)
        // 间距
        val advance = (pathMeasure.length - ConvertUtils.dp2px(2F)) / count
        // 改效果
        pathEffect = PathDashPathEffect(
            dash, advance, 0F,
            PathDashPathEffect.Style.ROTATE
        )
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        // 画弧线
        canvas?.drawArc(
            width / 2 - redius,
            height / 2 - redius,
            width / 2 + redius,
            height / 2 + redius,
            90 + angle / 2, 360 - angle,
            false,
            paint
        )
        paint.pathEffect = pathEffect
        // 画刻度
        canvas?.drawArc(
            width / 2 - redius,
            height / 2 - redius,
            width / 2 + redius,
            height / 2 + redius,
            90 + angle / 2, 360 - angle,
            false,
            paint
        )
        paint.pathEffect = null
        // 画指针
        canvas?.drawLine(
            width / 2F,
            height / 2F,
            (cos(Math.toRadians(getAngleFromMark(5))) * length ).toFloat(),
            (sin(Math.toRadians(getAngleFromMark(5))) * length ).toFloat(),
            paint
        )
    }

    /**
     * 根据刻度计算角度
     */
    private fun getAngleFromMark(mark: Int): Double {
        return (90 + angle / 2 + (360 - angle) / 20 * mark).toDouble()
    }
}