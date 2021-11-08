package com.prabhatkushwaha.mychef.framework.presentation.ui.views

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.ImageDecoder
import android.graphics.PorterDuff
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.Window
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.FloatRange
import androidx.core.graphics.drawable.DrawableCompat
import com.prabhatkushwaha.mychef.R
import android.view.WindowManager
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import coil.ImageLoader
import coil.imageLoader
import coil.request.ImageRequest
import com.prabhatkushwaha.mychef.framework.presentation.ui.common.BaseFragment


fun Context.getDrawableFromUrl(
    url: String?,
    paletteData: (drawable: Drawable, palette: Palette?) -> Unit
) {
    val request = ImageRequest.Builder(this)
        .data(url)
        .allowHardware(false)
        .target { drawable ->
            val task = Palette.Builder(drawable.toBitmap()).generate { palette ->
                paletteData.invoke(drawable, palette)
            }
        }
        .build()
    val disposable = imageLoader.enqueue(request)
}

fun Drawable.getPalette(data: (palette: Palette?) -> Unit) {
    Palette.Builder(this.toBitmap()).generate { palette ->
        data.invoke(palette)
    }
}

fun Activity.changeStatusBarColor(@ColorInt int: Int) {
    val window: Window = window
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = Color.BLUE
}

fun Context.resolveColor(@AttrRes attr: Int, fallback: Int = 0): Int {
    val a = theme.obtainStyledAttributes(intArrayOf(attr))
    try {
        return a.getColor(0, fallback)
    } finally {
        a.recycle()
    }
}

@ColorInt
fun accentColor(context: Context): Int {
    return context.resolveColor(R.attr.colorAccent, Color.parseColor("#263238"))
}

@ColorInt
fun withAlpha(@ColorInt baseColor: Int, @FloatRange(from = 0.0, to = 1.0) alpha: Float): Int {
    val a = Math.min(255, Math.max(0, (alpha * 255).toInt())) shl 24
    val rgb = 0x00ffffff and baseColor
    return a + rgb
}

fun createTintedDrawable(drawable: Drawable?, @ColorInt color: Int): Drawable? {
    var drawable = drawable
    if (drawable == null) {
        return null
    }
    drawable = DrawableCompat.wrap(drawable.mutate())
    DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_IN)
    DrawableCompat.setTint(drawable, color)
    return drawable
}