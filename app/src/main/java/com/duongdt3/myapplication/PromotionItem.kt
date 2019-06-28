package com.duongdt3.myapplication

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View


class PromotionItem @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val ovalArc = RectF()

    private val pointTL = Point(0, 0)
    private val pointTR = Point(width, 0)
    private val pointBL = Point(0, height)
    private val pointBR = Point(width, height)
    private val radius = 40.0f
    private val paintLine = Paint().apply {
        color = Color.RED
        style = Paint.Style.STROKE
        strokeWidth = 8.0f
    }

    private val paintFill = Paint().apply {
        color = Color.BLUE
        style = Paint.Style.FILL
    }
    private val myPath = Path()

    override fun onDraw(canvas: Canvas?) {
        //reset
        pointTL.set(0, 0)
        pointTR.set(width, 0)
        pointBL.set(0, height)
        pointBR.set(width, height)
        myPath.reset()

        //draw top line
        myPath.addRect(pointTL.x.toFloat() + radius, pointTL.y.toFloat(), pointTR.x.toFloat() - radius, pointTR.y.toFloat(), Path.Direction.CW)

        //draw right line
        myPath.addRect(pointTR.x.toFloat(), pointTR.y.toFloat() + radius, pointBR.x.toFloat(), pointBR.y.toFloat() - radius, Path.Direction.CW)

        //draw bottom line
        myPath.addRect(pointBL.x.toFloat() + radius, pointBL.y.toFloat(), pointBR.x.toFloat() - radius, pointBR.y.toFloat(), Path.Direction.CW)

        //draw left line
        myPath.addRect(pointTL.x.toFloat(), pointTL.y.toFloat() + radius, pointBL.x.toFloat(), pointBL.y.toFloat() - radius, Path.Direction.CW)


        //draw oval top left
        ovalArc.set(0 - radius, 0 - radius, 0 + radius, 0 + radius)
        myPath.addArc(ovalArc, 0f, 90f)

        //draw oval bottom left
        ovalArc.set(0f - radius, height - radius, 0f + radius, height + radius)
        myPath.addArc(ovalArc, 180 + 45 + 0f, 180f)

        //draw oval top right
        ovalArc.set(width - radius, 0 - radius, width + radius, 0 + radius)
        myPath.addArc(ovalArc, 90f, 90f)

        //draw oval bottom right
        ovalArc.set(width - radius, height - radius, width + radius, height + radius)
        myPath.addArc(ovalArc, 180f, 90f)

        canvas?.drawPath(myPath, paintLine)
        super.onDraw(canvas)
    }
}