package com.prabhatkushwaha.mychef.framework.presentation.ui.views

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.ShapeAppearanceModel
import com.prabhatkushwaha.mychef.R
import com.prabhatkushwaha.mychef.R.styleable.custom_styleable_imageview

class CustomStyleablImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,defStyle:Int=-1
) : ShapeableImageView(context, attrs,defStyle) {
    init {
        val typedArray =
            context.obtainStyledAttributes(attrs, custom_styleable_imageview, defStyle, -1)
        addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
            val radius = width / 2f
            shapeAppearanceModel = ShapeAppearanceModel().withCornerSize(radius)
        }
        typedArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
}