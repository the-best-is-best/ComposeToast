package io.github.tbib.compose_toast.native_toast

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.cValue
import kotlinx.cinterop.useContents
import platform.CoreGraphics.CGPoint
import platform.CoreGraphics.CGRect
import platform.UIKit.NSTextAlignmentCenter
import platform.UIKit.UIApplication
import platform.UIKit.UIColor
import platform.UIKit.UILabel
import platform.UIKit.UIScreen
import platform.UIKit.UIView
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
        val toastLabel = UILabel().apply {
            text = msg
            textColor = UIColor.whiteColor
            backgroundColor = UIColor.blackColor.colorWithAlphaComponent(0.6)
            numberOfLines = 0
            textAlignment = NSTextAlignmentCenter
            layer.cornerRadius = 10.0
            clipsToBounds = true
        }
        val screenHeight = UIScreen.mainScreen.bounds.useContents { this.size.height }
        val screenWidth = UIScreen.mainScreen.bounds.useContents { this.size.width }

        // Set the size of the toast
        val toastWidth = screenWidth - 50

        toastLabel.setFrame(cValue<CGRect> {
            this.origin.x = 0.0
            this.origin.y = 0.0
            this.size.width = toastWidth
            this.size.height = 50.0
        })
        // Get the root view controller
        val rootViewController = UIApplication.sharedApplication.keyWindow?.rootViewController

        // Calculate the position

        val centerX = screenWidth / 2
        val centerY = screenHeight - 100 // 100 points from the bottom

        // Set the center of the label with cValue
        toastLabel.center = cValue<CGPoint> {
            this.x = centerX
            this.y = centerY
        }
        // Add the label to the root view
        rootViewController?.view?.addSubview(toastLabel)

        // Set the duration for display
        val duration = if (type == NativeToastType.SHORT) 2.0 else 3.5

        // Animate the appearance and disappearance of the toast
        toastLabel.alpha = 0.0
        UIView.animateWithDuration(0.5) {
            toastLabel.alpha = 1.0
        }

        dispatch_after(
            dispatch_time(
                DISPATCH_TIME_NOW,
                (duration * NSEC_PER_SEC.toDouble()).toLong()
            ), dispatch_get_main_queue()
        ) {
            UIView.animateWithDuration(0.5) {
                toastLabel.alpha = 0.0
            }
        }
    }

}