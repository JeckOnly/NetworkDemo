package com.example.networkdemo.util

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.example.camerademo.R
import com.github.alexzhirkevich.customqrgenerator.QrData
import com.github.alexzhirkevich.customqrgenerator.style.Color
import com.github.alexzhirkevich.customqrgenerator.vector.QrCodeDrawable
import com.github.alexzhirkevich.customqrgenerator.vector.QrVectorOptions
import com.github.alexzhirkevich.customqrgenerator.vector.createQrVectorOptions
import com.github.alexzhirkevich.customqrgenerator.vector.style.*

fun String.urlToQRCode(context: Context): Drawable {
    val data = QrData.Url(this)
    val options = createQrVectorOptions {
        padding = .125f
        logo {
            drawable = ContextCompat
                .getDrawable(context, R.drawable.img)
            size = .25f
            padding = QrVectorLogoPadding.Natural(.2f)
            shape = QrVectorLogoShape
                .Circle
        }
    }
    return QrCodeDrawable(data, options)
}