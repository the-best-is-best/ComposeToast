package io.github.tbib.compose_toast.native_toast

import io.github.tbib.compose_toast.getAppLogoIOS
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.cValue
import kotlinx.cinterop.readValue
import kotlinx.cinterop.useContents
import platform.CoreGraphics.CGPoint
import platform.CoreGraphics.CGRect
import platform.CoreGraphics.CGRectZero
import platform.UIKit.NSTextAlignmentCenter
import platform.UIKit.UIApplication
import platform.UIKit.UIColor
import platform.UIKit.UIImageView
import platform.UIKit.UILabel
import platform.UIKit.UIScreen
import platform.UIKit.UIView
import platform.UIKit.UIViewContentMode
import platform.darwin.DISPATCH_TIME_NOW
import platform.darwin.NSEC_PER_SEC
import platform.darwin.dispatch_after
import platform.darwin.dispatch_get_main_queue
import platform.darwin.dispatch_time

actual object NativeShowToast {

    @OptIn(ExperimentalForeignApi::class)
    actual fun show(
        msg: String,
        type: NativeToastType
    ) {
        val toastView = UIView().apply {
            backgroundColor = UIColor.blackColor.colorWithAlphaComponent(0.6)
            layer.cornerRadius = 10.0
            clipsToBounds = true
        }

        // ====== النص ======
        val toastLabel = UILabel().apply {
            text = msg
            textColor = UIColor.whiteColor
            numberOfLines = 0
            textAlignment = NSTextAlignmentCenter
        }

        // ====== الصورة (اختياري) ======
        val logoImage = getAppLogoIOS()  // لو حابب تضيف شعار التطبيق
        val imageView = logoImage?.let {
            val imageView = UIImageView(frame = CGRectZero.readValue())
            imageView.image = it
            imageView.contentMode = UIViewContentMode.UIViewContentModeScaleAspectFit
            imageView
        }


        // ====== الحجم ======
        val screenWidth = UIScreen.mainScreen.bounds.useContents { size.width }
        val screenHeight = UIScreen.mainScreen.bounds.useContents { size.height }
        val toastWidth = screenWidth - 50
        val toastHeight = 50.0

        toastView.setFrame(cValue<CGRect> {
            origin.x = 0.0
            origin.y = 0.0
            size.width = toastWidth
            size.height = toastHeight
        })

        val padding = 10.0
        val imageSize = 30.0

        imageView?.setFrame(cValue<CGRect> {
            origin.x = padding
            origin.y = (toastHeight - imageSize) / 2
            size.width = imageSize
            size.height = imageSize
        })

        val labelX = if (imageView != null) padding * 2 + imageSize else padding
        toastLabel.setFrame(cValue<CGRect> {
            origin.x = labelX
            origin.y = 0.0
            size.width = toastWidth - labelX - padding
            size.height = toastHeight
        })

        // ====== تجميع العناصر ======
        imageView?.let { toastView.addSubview(it) }
        toastView.addSubview(toastLabel)

        // ====== العرض ======
        val centerX = screenWidth / 2
        val centerY = screenHeight - 100
        toastView.center = cValue<CGPoint> {
            x = centerX
            y = centerY
        }

        val rootView = UIApplication.sharedApplication.keyWindow?.rootViewController?.view
        rootView?.addSubview(toastView)

        // ====== الأنيميشن ======
        toastView.alpha = 0.0
        UIView.animateWithDuration(0.5) {
            toastView.alpha = 1.0
        }

        val duration = if (type == NativeToastType.SHORT) 2.0 else 3.5
        dispatch_after(
            dispatch_time(DISPATCH_TIME_NOW, (duration * NSEC_PER_SEC.toDouble()).toLong()),
            dispatch_get_main_queue()
        ) {
            UIView.animateWithDuration(0.5) {
                toastView.alpha = 0.0
            }
        }
    }
}
