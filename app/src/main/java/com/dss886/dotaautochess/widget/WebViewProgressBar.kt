package com.dss886.dotaautochess.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * Created by icyfox on 16/1/4.
 */
class WebViewProgressBar : View {

    private var progress: Int = 0
    private var p = Paint()

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun onDraw(canvas: Canvas) {
        val h = measuredHeight
        val w = measuredWidth
        p.color = Color.GREEN
        if (progress < 90) {
            p.alpha = 255
        } else {
            p.alpha = 2500 - 25 * progress
        }

        val drawW = (w.toDouble() * progress / 100).toInt()
        canvas.drawRect(0f, 0f, drawW.toFloat(), h.toFloat(), p)

        super.onDraw(canvas)
    }

    fun setProgress(progress: Int) {
        this.progress = progress
        invalidate()
    }

}
