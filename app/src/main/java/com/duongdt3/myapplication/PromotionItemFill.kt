package com.duongdt3.myapplication

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View


class PromotionItemFill @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val radius = 50.0f
    private val paintLine = Paint().apply {
        color = Color.RED
        style = Paint.Style.STROKE
        isAntiAlias = true
        strokeWidth = 2.0f
    }

    private val paintLineControlPoints = Paint().apply {
        color = Color.BLUE
        style = Paint.Style.STROKE
        isAntiAlias = true
        strokeWidth = 1.0f
    }

    private val paintFill = Paint().apply {
        color = Color.BLUE
        style = Paint.Style.FILL
    }
    private val myPath = Path()

    private val isDrawControlPoints = false

    override fun onDraw(canvas: Canvas?) {
        myPath.reset()
        myPath.fillType = Path.FillType.EVEN_ODD

        val left = 0f
        val right = width.toFloat()
        val top = 0f
        val bottom = height.toFloat()

        myPath.moveTo(left + radius, top)
        myPath.lineTo(right - radius, top)
        myPath.moveTo(right - radius, top)
        myPath.cubicTo(right - radius, top + (radius / 2), right - (radius / 2), top + radius, right, top + radius)
        if (isDrawControlPoints) {
            canvas?.drawLine(right - radius, top, right - radius, top + (radius / 2), paintLineControlPoints)
            canvas?.drawLine(right, top + radius, right - (radius / 2), top + radius, paintLineControlPoints)
        }

        myPath.moveTo(right, top + radius)
        myPath.lineTo(right, bottom - radius)
        myPath.moveTo(right, bottom - radius)
        myPath.cubicTo(right - (radius / 2), bottom - radius, right - radius, bottom - (radius / 2), right - radius, bottom)
        if (isDrawControlPoints) {
            canvas?.drawLine(right, bottom - radius, right - (radius / 2), bottom - radius, paintLineControlPoints)
            canvas?.drawLine(right - radius, bottom, right - radius, bottom - (radius / 2), paintLineControlPoints)
        }

        myPath.moveTo(right - radius, bottom)
        myPath.lineTo(left + radius, bottom)
        myPath.moveTo(left + radius, bottom)
        myPath.cubicTo(left + radius, bottom - (radius / 2), left + (radius / 2), bottom - radius, left, bottom - radius)

        if (isDrawControlPoints) {
            canvas?.drawLine(left + radius, bottom, left + radius, bottom - (radius / 2), paintLineControlPoints)
            canvas?.drawLine(left, bottom - radius, left + (radius / 2), bottom - radius, paintLineControlPoints)
        }

        myPath.moveTo(left, bottom - radius)
        myPath.lineTo(left, top + radius)
        myPath.moveTo(left, top + radius)
        myPath.cubicTo(left + (radius / 2), top + radius, left + radius, top + (radius / 2), left + radius, top)
        if (isDrawControlPoints) {
            canvas?.drawLine(left, top + radius, left + (radius / 2), top + radius, paintLineControlPoints)
            canvas?.drawLine(left + radius, top, left + radius, top + (radius / 2), paintLineControlPoints)
        }
        myPath.moveTo(left + radius, top)
        myPath.close()

        canvas?.drawPath(myPath, paintLine)
        //canvas?.clipPath(myPath)
        //canvas?.drawColor(Color.parseColor("#A6000000"))
        super.onDraw(canvas)
    }
}