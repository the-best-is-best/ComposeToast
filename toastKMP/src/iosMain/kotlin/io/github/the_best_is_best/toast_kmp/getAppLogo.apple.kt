package io.github.the_best_is_best.toast_kmp

import platform.Foundation.NSBundle
import platform.UIKit.UIImage

fun getAppLogoIOS(): UIImage? {
    val bundle = NSBundle.mainBundle()
    val iconFiles = bundle.objectForInfoDictionaryKey("CFBundleIcons") as? Map<*, *>
    val primaryIcon = iconFiles?.get("CFBundlePrimaryIcon") as? Map<*, *>
    val iconFileNames = primaryIcon?.get("CFBundleIconFiles") as? List<*>

    // Get the name of the first icon file, if available
    val iconName = iconFileNames?.firstOrNull() as? String
    return iconName?.let {
        UIImage.imageNamed(it)
    }
}
