package net.dirox.coreproject.utils.customwidget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.math.roundToInt



class CircleProgressTimeline : View {


    constructor(context: Context) : super(context)

    constructor(context: Context, attr: AttributeSet) : super(context, attr)

    constructor(context: Context, attr: AttributeSet, defStyle: Int) : super(context, attr, defStyle)

    private var startAngle = 185f

    private var endAngle = 0f

    val radius = 450f

    var totalWidth: Float = 0f

    @Suppress("DEPRECATION")
    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val x = width.toFloat()
        val y = height.toFloat()
        val tlPaint = Paint()
        val dashPaint = Paint()

        this.totalWidth = width.toFloat()

        val floatArr = floatArrayOf(1f,33f)

        dashPaint.apply {
            strokeWidth = 40f
            pathEffect = DashPathEffect(floatArr,0f)
            style = Paint.Style.STROKE
            color = Color.BLACK
        }

        tlPaint.apply {
            strokeWidth = 40.0f
            isDither = true
            strokeCap = Paint.Cap.ROUND
            pathEffect = CornerPathEffect(50f)
            style = Paint.Style.STROKE
            shader = RadialGradient(x, y,
                y * 2,
                resources.getColor(android.R.color.holo_red_light),
                resources.getColor(android.R.color.holo_orange_light),
                Shader.TileMode.MIRROR)
        }

        val oval = RectF()

        oval.set(
            20f,
            y - radius,
            x - 20f,
            y + radius
        )


        canvas?.drawArc(oval, startAngle, 175f, false, dashPaint)

        canvas?.drawArc(oval, startAngle, endAngle, false, tlPaint)
    }

    fun increaseEndAngle(total: Float) {



        this.endAngle = total.roundToInt().toFloat()

        invalidate()
    }

}