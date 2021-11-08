package com.prabhatkushwaha.mychef.framework.presentation.ui.views

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.RippleDrawable
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.prabhatkushwaha.mychef.R

class BottomNavigationBarTinted @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BottomNavigationView(context, attrs, defStyleAttr) {

    init {
        //labelVisibilityMode =BottomNavigationView.LABEL_VISIBILITY_LABELED

        val iconColor = context.resolveColor(android.R.attr.colorControlNormal)
        val accentColor =
            context.resolveColor(R.attr.colorAccent, Color.parseColor("#1FCC79"))
        NavigationViewUtil.setItemIconColors(
            this,
            withAlpha(iconColor, 0.5f),
            accentColor
        )
        NavigationViewUtil.setItemTextColors(
            this,
            withAlpha(iconColor, 0.5f),
            accentColor
        )
        itemBackground = RippleDrawable(
            RippleUtils.convertToRippleDrawableColor(
                ColorStateList.valueOf(
                    accentColor(context).addAlpha()
                )
            ),
            ContextCompat.getDrawable(context, R.drawable.bottom_navigation_item_background),
            ContextCompat.getDrawable(context, R.drawable.bottom_navigation_item_background_mask)
        )
        setOnApplyWindowInsetsListener(null)
        //itemRippleColor = ColorStateList.valueOf(accentColor)
        background = ColorDrawable(context.resolveColor(R.attr.colorSurface))
    }
}

fun Int.addAlpha(): Int {
    return withAlpha(this, 0.12f)
}