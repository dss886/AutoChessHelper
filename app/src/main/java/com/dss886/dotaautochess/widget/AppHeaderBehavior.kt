package com.dss886.dotaautochess.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.dss886.dotaautochess.R
import com.google.android.material.appbar.AppBarLayout

/**
 * Created by dss886 on 2019/1/26.
 */
@Suppress("unused", "UNUSED_PARAMETER")
class AppHeaderBehavior @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : CoordinatorLayout.Behavior<AppHeaderView>() {

    private val mHeaderMoveDistance = context.resources.getDimension(R.dimen.hero_header_height) - context.resources.getDimension(R.dimen.toolbar_height)

    override fun layoutDependsOn(parent: CoordinatorLayout, child: AppHeaderView, dependency: View): Boolean {
        return dependency is AppBarLayout
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: AppHeaderView, dependency: View): Boolean {
        if (mHeaderMoveDistance == 0f) {
            return false
        }
        var percent = -dependency.y / mHeaderMoveDistance
        if (percent < 0) {
            percent = 0f
        }
        child.onCollapsing(percent)
        return true
    }
}
