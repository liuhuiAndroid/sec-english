package com.seckill.common.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.seckill.common.utilities.ConvertUtils
import kotlin.math.cos
import kotlin.math.sin

/**
 * 饼图
 */
class PieChartView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    // 抗锯齿标志，一般都要设置，否则会有毛边
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    // 矩形
    private val bounds = RectF()

    // 饼图半径
    private val radius = 200F

    // 角度
    private val angles = intArrayOf(60, 100, 120, 80)

    // 偏移距离
    private val length = ConvertUtils.dp2px(8F)

    // 第几个拉出来
    private val pulledOutIndex = 2

    // 颜色
    private val colors = intArrayOf(
        Color.parseColor("#2979FF"), Color.parseColor("#FF0000"),
        Color.parseColor("#999999"), Color.parseColor("#00FA00")
    )

    /**
     * 在视图的大小发生改变时调用该方法
     */
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        bounds.set(
            (width / 2) - radius,
            (height / 2) - radius,
            (width / 2) + radius,
            (height / 2) + radius
        )
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        var currentAngle = 0F
        angles.forEachIndexed { index, value ->
            paint.color = colors[index]
            // save():保存当前的绘图状态
            canvas?.save()
            if (index == pulledOutIndex) {
                canvas?.translate(
                    (cos(Math.toRadians((currentAngle + value / 2).toDouble())) * length).toFloat(),
                    (sin(Math.toRadians((currentAngle + value / 2).toDouble())) * length).toFloat()
                )
            }
            canvas?.drawArc(
                bounds, currentAngle,
                value.toFloat(), true, paint
            )
            // restore():恢复之前保存的绘图状态
            canvas?.restore()
            currentAngle += value
        }
    }

}