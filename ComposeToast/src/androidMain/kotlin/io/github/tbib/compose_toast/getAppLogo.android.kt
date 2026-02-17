package io.github.tbib.compose_toast

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import io.github.the_best_is_best.toast_kmp.getAppLogo

internal actual fun getAppLogo(): ImageBitmap? {
    return getAppLogo()?.asAndroidBitmap()?.asImageBitmap()
}