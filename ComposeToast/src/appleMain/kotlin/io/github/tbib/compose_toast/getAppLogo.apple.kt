package io.github.tbib.compose_toast

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.get
import org.jetbrains.skia.ColorAlphaType
import org.jetbrains.skia.ColorSpace
import org.jetbrains.skia.ColorType
import org.jetbrains.skia.Image
import org.jetbrains.skia.ImageInfo
import platform.CoreFoundation.CFDataGetBytePtr
import platform.CoreFoundation.CFDataGetLength
import platform.CoreFoundation.CFRelease
import platform.CoreGraphics.CGDataProviderCopyData
import platform.CoreGraphics.CGImageAlphaInfo
import platform.CoreGraphics.CGImageGetAlphaInfo
import platform.CoreGraphics.CGImageGetBytesPerRow
import platform.CoreGraphics.CGImageGetDataProvider
import platform.CoreGraphics.CGImageGetHeight
import platform.CoreGraphics.CGImageGetWidth
import platform.Foundation.NSBundle
import platform.UIKit.UIImage

actual fun getAppLogo(): ImageBitmap? {
    val bundle = NSBundle.mainBundle()
    val iconFiles = bundle.objectForInfoDictionaryKey("CFBundleIcons") as? Map<*, *>
    val primaryIcon = iconFiles?.get("CFBundlePrimaryIcon") as? Map<*, *>
    val iconFileNames = primaryIcon?.get("CFBundleIconFiles") as? List<*>

    // Get the name of the first icon file, if available
    val iconName = iconFileNames?.firstOrNull() as? String
    return iconName?.let { UIImage.imageNamed(it)?.toImageBitmap() }
}

fun UIImage.toImageBitmap(): ImageBitmap {
    val skiaImage = this.toSkiaImage() ?: return ImageBitmap(1, 1)
    return skiaImage.toComposeImageBitmap()
}

@OptIn(ExperimentalForeignApi::class)
private fun UIImage.toSkiaImage(): Image? {
    val imageRef = this.CGImage ?: return null

    // Get image dimensions
    val width = CGImageGetWidth(imageRef).toInt()
    val height = CGImageGetHeight(imageRef).toInt()

    // Get data provider and copy data
    val dataProvider = CGImageGetDataProvider(imageRef) ?: return null
    val data = CGDataProviderCopyData(dataProvider) ?: return null

    // Get byte pointer and length
    val bytePointer = CFDataGetBytePtr(data) ?: return null
    val length = CFDataGetLength(data)

    // Determine alpha type
    val alphaType = when (CGImageGetAlphaInfo(imageRef)) {
        CGImageAlphaInfo.kCGImageAlphaPremultipliedFirst,
        CGImageAlphaInfo.kCGImageAlphaPremultipliedLast -> ColorAlphaType.PREMUL

        CGImageAlphaInfo.kCGImageAlphaFirst,
        CGImageAlphaInfo.kCGImageAlphaLast -> ColorAlphaType.UNPREMUL

        CGImageAlphaInfo.kCGImageAlphaNone,
        CGImageAlphaInfo.kCGImageAlphaNoneSkipFirst,
        CGImageAlphaInfo.kCGImageAlphaNoneSkipLast -> ColorAlphaType.OPAQUE

        else -> ColorAlphaType.UNKNOWN
    }

    // Create byte array from pointer
    val byteArray = ByteArray(length.toInt())
    for (index in byteArray.indices) {
        byteArray[index] = bytePointer[index].toByte()
    }

    // Release the data reference
    CFRelease(data)

    val skiaColorSpace = ColorSpace.sRGB
    val colorType = ColorType.RGBA_8888

    // Convert RGBA to BGRA
    for (i in byteArray.indices step 4) {
        if (i + 3 < byteArray.size) {
            val r = byteArray[i]
            byteArray[i] = byteArray[i + 2]  // b
            byteArray[i + 2] = r  // r
        }
    }

    return Image.makeRaster(
        imageInfo = ImageInfo(
            width = width,
            height = height,
            colorType = colorType,
            alphaType = alphaType,
            colorSpace = skiaColorSpace
        ),
        bytes = byteArray,
        rowBytes = CGImageGetBytesPerRow(imageRef).toInt(),
    )
}
