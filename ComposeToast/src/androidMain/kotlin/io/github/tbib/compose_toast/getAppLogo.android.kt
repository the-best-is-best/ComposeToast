package io.github.tbib.compose_toast

import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.core.graphics.drawable.toBitmap


actual fun getAppLogo(): ImageBitmap? {
    try {
        val iconDrawable: Drawable =
            AndroidLogoToast.getActivity().packageManager.getApplicationIcon(AndroidLogoToast.getActivity().applicationInfo.packageName)
        val bitmapDrawable = iconDrawable.toBitmap()
        val imageBitmap = bitmapDrawable.asImageBitmap()
        return imageBitmap
    } catch (e: Exception) {
        println("error get logo ${e}")
        return null
    }
}

