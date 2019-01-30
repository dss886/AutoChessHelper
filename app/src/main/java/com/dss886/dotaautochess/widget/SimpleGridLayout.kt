package com.dss886.dotaautochess.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import com.dss886.dotaautochess.R

/**
 * Created by dss886 on 2019/1/27.
 *
 * GridView and GridLayout cannot divide item on average (have 5~10px gap).
 * (Maybe it is that I didn't find the right way to use it...)
 * So I have to measure and layout these imageViews by myself.
 */
open class SimpleGridLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : ViewGroup(context, attrs, defStyleAttr) {

    private var mColumnCount: Int = 0
    private var mItemMargin: Float = 0F
    private var mItemRatioWidth: Int = 0
    private var mItemRatioHeight: Int = 0

    init {
        if (attrs != null) {
            val array = context.theme.obtainStyledAttributes(attrs, R.styleable.SimpleGridLayout, 0, 0)
            mColumnCount = array.getInt(R.styleable.SimpleGridLayout_columnCount, 5)
            mItemMargin = array.getDimension(R.styleable.SimpleGridLayout_itemMargin, 0f)
            mItemRatioWidth = array.getInt(R.styleable.SimpleGridLayout_itemRatioWidth, 0)
            mItemRatioHeight = array.getInt(R.styleable.SimpleGridLayout_itemRatioHeight, 0)
            array.recycle()
        }
    }

    override fun generateDefaultLayoutParams(): ViewGroup.LayoutParams {
        return ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val childCount = childCount
        val lineCount = ((childCount - 1) * 1f / mColumnCount).toInt() + 1
        val width = View.MeasureSpec.getSize(widthMeasureSpec).toFloat()
        val childWidth = (width - (mColumnCount - 1) * mItemMargin) / mColumnCount
        val childHeight = childWidth * mItemRatioHeight / mItemRatioWidth
        val childWidthMeasureSpec = View.MeasureSpec.makeMeasureSpec(childWidth.toInt(), View.MeasureSpec.EXACTLY)
        val childHeightMeasureSpec = View.MeasureSpec.makeMeasureSpec(childHeight.toInt(), View.MeasureSpec.EXACTLY)
        val newHeight = childHeight * lineCount + mItemMargin * (lineCount - 1)
        super.onMeasure(widthMeasureSpec, View.MeasureSpec.makeMeasureSpec(newHeight.toInt(), View.MeasureSpec.EXACTLY))
        measureChildren(childWidthMeasureSpec, childHeightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var left = 0
        var top = 0
        var lineCount = 0
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            child.layout(left, top, left + child.measuredWidth, top + child.measuredHeight)
            if ((i + 1) % mColumnCount == 0) {
                lineCount++
                left = 0
                top = (child.measuredHeight + mItemMargin.toInt()) * lineCount
            } else {
                left += child.measuredWidth + mItemMargin.toInt()
            }
        }
    }

}
