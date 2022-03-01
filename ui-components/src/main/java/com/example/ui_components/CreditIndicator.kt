package com.example.ui_components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout


/**
 * Credit Indicator component
 */
@Suppress("MemberVisibilityCanBePrivate")
class CreditIndicator @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    val paint1 = Paint().apply {
        color = Color.RED
        style = Paint.Style.STROKE
        strokeWidth = 10f
    }



    val paint = Paint().apply {
        color = Color.RED
        style = Paint.Style.STROKE
        strokeWidth = 10f
    }

    override fun dispatchDraw(canvas: Canvas?) {

        val x = width
        val y = height
        val radius: Int = width/2 - 100
        paint.style = Paint.Style.FILL
        paint.color = Color.WHITE
        canvas!!.drawPaint(paint)
        // Use Color.parseColor to define HTML colors
        // Use Color.parseColor to define HTML colors
        paint.color = Color.parseColor("#CD5C5C")
        canvas!!.drawCircle((x / 2).toFloat(), (y / 2).toFloat(), radius.toFloat(), paint1)

//        canvas?.drawCircle(200f, 100f, 100f, paint)

        super.dispatchDraw(canvas)

    }

    init {
        inflate(context, R.layout.credit_indicator, this)
        invalidate()
    }

}