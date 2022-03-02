package com.example.ui_components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.core.view.doOnLayout
import com.google.android.material.progressindicator.CircularProgressIndicator

/**
 * Credit Indicator component
 */
@Suppress("MemberVisibilityCanBePrivate")
class DonutIndicator @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    val title by lazy<TextView> { findViewById(R.id.textviewTitle) }
    val subtitle by lazy<TextView> { findViewById(R.id.textviewSubTitle) }
    val value by lazy<TextView> { findViewById(R.id.textviewValue) }
    val indicator by lazy<CircularProgressIndicator> { findViewById(R.id.progressIndicator) }
    private val frame by lazy<FrameLayout> { findViewById(R.id.frame) }


    private val paint = Paint().apply {
        color = ContextCompat.getColor(this@DonutIndicator.context, R.color.primaryColor)
        style = Paint.Style.STROKE
        strokeWidth = PAINT_STROKE_WIDTH
    }

    override fun dispatchDraw(canvas: Canvas?) {
        super.dispatchDraw(canvas)
        val x = width
        val y = height
        val radius: Int = frame.width / 2 + PADDING_INDICATOR_OUTER_CIRCLE
        canvas?.drawCircle((x / 2).toFloat(), (y / 2).toFloat(), radius.toFloat(), paint)
    }

    init {
        inflate(context, R.layout.donut_indicator, this)
        attrs?.let {
            val attributes = context.obtainStyledAttributes(it, R.styleable.DonutIndicator)
            // Apply attributes
            with(attributes) {
                // Title / description
                getString(R.styleable.DonutIndicator_title)?.run { title.text = this }
                getString(R.styleable.DonutIndicator_subtitle)?.run { subtitle.text = this }
                getString(R.styleable.DonutIndicator_value)?.run { value.text = this }
                recycle()
            }
        }

        // Override Spinner size (outer diameter)
        rootView.doOnLayout {
            indicator.indicatorSize = frame.width
        }
        invalidate()
    }

    companion object {
        const val PADDING_INDICATOR_OUTER_CIRCLE = 10
        const val PAINT_STROKE_WIDTH = 10f
    }
}