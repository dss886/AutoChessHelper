package com.dss886.dotaautochess.utils

import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dss886.dotaautochess.app.App
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by dss886 on 2019/1/30.
 */

inline val Int.dp: Float
    get() = (App.inst.resources.displayMetrics.density * this) + 0.5f

inline val Int.dpInt: Int
    get() = this.dp.toInt()

fun ImageView.loadImage(resourceId: Int) {
    Glide.with(this)
            .load(resourceId)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
}

fun ImageView.loadImage(url : String?) {
    url ?: return
    Glide.with(this)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
}

fun Int.toColor(): Int {
    return ContextCompat.getColor(App.inst, this)
}

fun Long.toFullTime(): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA)
    return sdf.format(Date(this * 1000))
}

fun Long.toShortTime(): String {
    val calendar = Calendar.getInstance()
    val nowTime = (calendar.time.time / 1000)
    val t = nowTime - this
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val minute = calendar.get(Calendar.MINUTE)
    val second = calendar.get(Calendar.SECOND)
    val s = (hour * 60 + minute) * 60 + second
    val df = SimpleDateFormat("HH:mm", Locale.CHINA)
    val df2 = SimpleDateFormat("MM月dd日", Locale.CHINA)
    return when {
        t < 15 -> "刚刚"
        t < 60 -> "$t 秒前"
        t < 60 * 60 -> (t / 60).toString() + " 分钟前"
        t < s -> "今天 " + df.format(Date(this * 1000))
        t < s + 24 * 60 * 60 -> "昨天 " + df.format(Date(this * 1000))
        else -> df2.format(Date(this * 1000))
    }
}

fun CharSequence.trimTrailingWhitespace(): CharSequence {
    var i = length
    while (--i >= 0 && Character.isWhitespace(this[i])) {}
    return this.subSequence(0, i + 1)
}