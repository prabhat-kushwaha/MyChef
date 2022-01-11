package com.prabhatkushwaha.mychef.framework.presentation.ui.views

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import java.io.ByteArrayOutputStream

import android.graphics.drawable.ColorDrawable

import android.R
import android.R.string
import android.graphics.Color
import android.view.View
import com.prabhatkushwaha.mychef.databinding.DialogWebViewBinding
import androidx.core.content.ContextCompat.startActivity

import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity





class UiUtils {
    companion object {
        fun Bitmap?.getBase64(): String {
            if (this == null) return ""
            val bos = ByteArrayOutputStream()
            this.compress(CompressFormat.PNG, 100, bos)
            val bb = bos.toByteArray()
            return Base64.encodeToString(bb, Base64.DEFAULT)
        }

        fun String.getBitmap(): Bitmap {
            val imageBytes = Base64.decode(this, Base64.DEFAULT)
            return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        }

        fun Context.displayWebView(url: String) {
            val myIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            this.startActivity(myIntent)


        }
    }


}