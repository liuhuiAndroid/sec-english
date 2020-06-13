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

    // 反锯齿，一般都要设置，否则会有毛边
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    // 矩形
    private val bounds = RectF()

    // 饼图半径
    private val redius = 200F

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

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        bounds.set(
            (width / 2) - redius,
            (height / 2) - redius,
            (width / 2) + redius,
            (height / 2) + redius
        )
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        var currentAngle = 0F
        angles.forEachIndexed { index, value ->
            paint.color = colors[index]
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
            canvas?.restore()
            currentAngle += value
        }
    }

}