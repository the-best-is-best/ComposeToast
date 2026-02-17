package io.github.tbib.compose_toast.toast_ui

import io.github.the_best_is_best.toast_kmp.NativeShowToast
import io.github.the_best_is_best.toast_kmp.NativeToastType

actual object NativeShowToastCMP {
    actual fun show(
        msg: String,
        nativeToastType: NativeToastTypeCMP
    ) {
        NativeShowToast.show(
            msg,
            nativeToastType.toNativeToastType()
        )
    }
}

fun NativeToastTypeCMP.toNativeToastType(): NativeToastType {
    return when (this) { // Use 'this' to refer to the instance of NativeToastTypeCMP
        NativeToastTypeCMP.LONG -> NativeToastType.LONG
        NativeToastTypeCMP.SHORT -> NativeToastType.SHORT
    }
}