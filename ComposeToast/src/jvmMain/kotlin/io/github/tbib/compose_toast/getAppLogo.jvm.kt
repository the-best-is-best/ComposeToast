package io.github.tbib.compose_toast

import androidx.compose.ui.graphics.ImageBitmap
import java.io.InputStream

object JVMComposeToast {
    var appLogoPath: InputStream? = null
}

// Function to get the application logo
actual fun getAppLogo(): ImageBitmap? {
    return null
}
