package io.github.tbib.compose_toast.native_toast

import kotlinx.browser.window

actual object NativeShowToast {
    actual fun show(
        msg: String,
        type: NativeToastType
    ) {
        window.alert(msg)

    }
}