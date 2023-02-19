package com.vk.directop.lifecyclealeks


import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import java.util.*
import kotlin.math.cos
import kotlin.math.sin


class AstoneClock
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var padding = 0
    private var fontSize = 0
    private val numeralSpacing = 0
    private var handTruncation = 0
    private var hourHandTruncation = 0
    private var radius = 0f
    lateinit var paint: Paint
    private var isInit = false
    private val numbers = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
    private val rect: Rect = Rect()


    private fun initClock() {
        padding = numeralSpacing + 50
        fontSize = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP, 13f,
            resources.displayMetrics
        ).toInt()
        val min = height.coerceAtMost(width)
        radius = min / 2.2f - padding
        handTruncation = min / 20
        hourHandTruncation = min / 7
        paint = Paint()
        isInit = true
    }

    override fun onDraw(canvas: Canvas) {
        if (!isInit) {
            initClock()
        }
        canvas.drawColor(Color.WHITE)
        drawCircle(canvas)
        drawCenter(canvas)
        drawClockStroke(canvas)
        drawHands(canvas)
        postInvalidateDelayed(500)
        invalidate()
    }

    private fun drawHand(canvas: Canvas, loc: Double, arrowType: ClockArrow) {
        val angle = Math.PI * loc / 30 - Math.PI / 2
//        val handRadius =
//            if (isHour) radius - handTruncation - hourHandTruncation else radius - handTruncation
        val handRadius  = when (arrowType){
            ClockArrow.HOUR -> 300f
            ClockArrow.MINUTE -> 54f
            ClockArrow.SECOND -> 200f
        }
        canvas.drawLine(
            width / 2f - (cos(angle) * 50).toFloat(),
            height / 2f - (sin(angle) * 50).toFloat(),
            (width / 2 + cos(angle) * handRadius).toFloat(),
            (height / 2 + sin(angle) * handRadius).toFloat(),
            paint
        )
    }

    private fun drawHands(canvas: Canvas) {
        val c = Calendar.getInstance()
        var hour = c[Calendar.HOUR_OF_DAY].toFloat()
        hour = if (hour > 12) hour - 12 else hour
        drawHand(canvas, ((hour + c[Calendar.MINUTE] / 60) * 5f).toDouble(), ClockArrow.HOUR)
        paint.color = Color.BLUE
        drawHand(canvas, c[Calendar.MINUTE].toDouble(), ClockArrow.MINUTE)
        paint.color = Color.RED
        drawHand(canvas, c[Calendar.SECOND].toDouble(), ClockArrow.SECOND)
    }

    private fun drawClockStroke(canvas: Canvas) {
        paint.textSize = fontSize.toFloat()
        for (number in numbers) {
            paint.strokeWidth = 20f
            paint.color =Color.BLACK

            val angle = Math.PI / 6 * (number - 3)
            val x = (width / 2 + cos(angle) * radius).toFloat()
            val y = (height / 2 + sin(angle) * radius).toFloat()
            val xEnd = (width / 2 + cos(angle) * (radius + 40)).toFloat()
            val yEnd = (height / 2 + sin(angle) * (radius + 40)).toFloat()
            canvas.drawLine(x, y, xEnd, yEnd, paint)
        }
    }

    private fun drawCenter(canvas: Canvas) {
        paint.style = Paint.Style.FILL
        canvas.drawCircle(width / 2f, height / 2f, 12f, paint)
    }

    private fun drawCircle(canvas: Canvas) {
        paint.reset()
        paint.color = Color.BLACK
        paint.strokeWidth = 20f
        paint.style = Paint.Style.STROKE
        paint.isAntiAlias = true
        canvas.drawCircle(width / 2f, height / 2f, radius + padding - 10f, paint)
    }
}

enum class ClockArrow{
    HOUR, MINUTE, SECOND
}