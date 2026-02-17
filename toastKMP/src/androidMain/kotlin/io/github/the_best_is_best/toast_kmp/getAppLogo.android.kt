package io.github.the_best_is_best.toast_kmp

import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.core.graphics.drawable.toBitmap


fun getAppLogo(): ImageBitmap? {
    try {
        val iconDrawable: Drawable =
            applicationContext.packageManager.getApplicationIcon(applicationContext.applicationInfo.packageName)
        val bitmapDrawable = iconDrawable.toBitmap()
        val imageBitmap = bitmapDrawable.asImageBitmap()
        return imageBitmap
    } catch (e: Exception) {
        println("error get logo ${e}")
        return null
    }
}

