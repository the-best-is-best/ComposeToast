package io.github.tbib.compose_toast

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import javax.imageio.ImageIO

internal actual fun getAppLogo(): ImageBitmap? {
    return try {
        // Single reliable method that works everywhere
        val url = object {}.javaClass.getResource("/images/app_logo.png")
            ?: return null

        url.openStream().use { stream ->
            ImageIO.read(stream)?.toComposeImageBitmap()
        }
    } catch (e: Exception) {
        null // Complete silence on failure
    }
}