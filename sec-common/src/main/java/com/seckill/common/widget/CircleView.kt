package com.seckill.common.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.seckill.common.utilities.ConvertUtils

class CircleView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    // 抗锯齿标志，一般都要设置，否则会有毛边
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val path = Path()

    /**
     * Path.Direction.CW 顺时针
     * Path.Direction.CCW 逆时针
     */
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        path.reset()
        path.addRect(
            (width / 2) - 150F, (height / 2) - 300F,
            (width / 2) + 150F, ((height / 2).toFloat()),
            Path.Direction.CCW
        )
        path.addCircle((width / 2).toFloat(), (height / 2).toFloat(), 150F, Path.Direction.CW)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        // 绘制圆
//        canvas?.drawCircle(
//            (width / 2).toFloat(),
//            (height / 2).toFloat(),
//            ConvertUtils.dp2px(100F),
//            paint
//        )
        // 从图片内部向外发送一条射线，交点顺时针和逆时针经过的数量相等则为内部，否则为外部
        path.fillType = Path.FillType.WINDING
        // EVEN_ODD 意味着如果用一条直线横贯图形时，外部和内部交替出现
        path.fillType = Path.FillType.EVEN_ODD
        // 绘制路径
        canvas?.drawPath(path, paint)
    }
}