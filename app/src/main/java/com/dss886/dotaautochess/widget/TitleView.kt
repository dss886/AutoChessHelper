package com.dss886.dotaautochess.widget

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.dss886.dotaautochess.R

/**
 * Created by dss886 on 2019/1/29.
 */
class TitleView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr) {

    private var mTitleTextView: TextView

    init {
        View.inflate(context, R.layout.widget_title_view, this)
        mTitleTextView = findViewById(com.dss886.dotaautochess.R.id.title)
        if (attrs != null) {
            val textAttrId = intArrayOf(android.R.attr.text)
            val array = context.theme.obtainStyledAttributes(attrs, textAttrId, 0, 0)
            val text = array.getText(0)
            mTitleTextView.text = text
            array.recycle()
        }
        gravity = Gravity.CENTER_VERTICAL
    }

    fun setText(text: CharSequence) {
        mTitleTextView.text = text
    }
}
