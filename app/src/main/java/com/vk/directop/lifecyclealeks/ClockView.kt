package com.vk.directop.lifecyclealeks

import android.R
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.opengl.ETC1
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import java.util.*
import kotlin.math.cos
import kotlin.math.sin


class ClockView

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
    private var radius = 0
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
        radius = min / 2 - padding
        handTruncation = min / 20
        hourHandTruncation = min / 7
        paint = Paint()
        isInit = true
    }

    override fun onDraw(canvas: Canvas) {
        if (!isInit) {
            initClock()
        }
        canvas.drawColor(Color.BLACK)
        drawCircle(canvas)
        drawCenter(canvas)
        drawNumeral(canvas)
        drawHands(canvas)
        postInvalidateDelayed(500)
        invalidate()
    }

    private fun drawHand(canvas: Canvas, loc: Double, isHour: Boolean) {
        val angle = Math.PI * loc / 30 - Math.PI / 2
        val handRadius =
            if (isHour) radius - handTruncation - hourHandTruncation else radius - handTruncation
        canvas.drawLine(
            width / 2f,
            height / 2f,
            (width / 2 + cos(angle) * handRadius).toFloat(),
            (height / 2 + sin(angle) * handRadius).toFloat(),
            paint
        )
    }

    private fun drawHands(canvas: Canvas) {
        val c = Calendar.getInstance()
        var hour = c[Calendar.HOUR_OF_DAY].toFloat()
        hour = if (hour > 12) hour - 12 else hour
        drawHand(canvas, ((hour + c[Calendar.MINUTE] / 60) * 5f).toDouble(), true)
        drawHand(canvas, c[Calendar.MINUTE].toDouble(), false)
        drawHand(canvas, c[Calendar.SECOND].toDouble(), false)
    }

    private fun drawNumeral(canvas: Canvas) {
        paint.textSize = fontSize.toFloat()
        for (number in numbers) {
            val tmp = number.toString()
            paint.getTextBounds(tmp, 0, tmp.length, rect)
            val angle = Math.PI / 6 * (number - 3)
            val x = (width / 2 + cos(angle) * radius - rect.width() / 2) .toFloat()
            val y = (height / 2 + sin(angle) * radius + rect.height() / 2).toFloat()
            canvas.drawText(tmp, x, y, paint)
        }
    }

    private fun drawCenter(canvas: Canvas) {
        paint.style = Paint.Style.FILL
        canvas.drawCircle(width / 2f, height / 2f, 12f, paint)
    }

    private fun drawCircle(canvas: Canvas) {
        paint.reset()
        paint.color = Color.WHITE
        paint.strokeWidth = 5F
        paint.style = Paint.Style.STROKE
        paint.isAntiAlias = true
        canvas.drawCircle(width / 2f, height / 2f, radius + padding - 10f, paint)
    }
}