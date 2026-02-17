package io.github.tbib.compose_toast.toast_ui

import io.github.the_best_is_best.toast_kmp.NativeShowToastJs
import io.github.the_best_is_best.toast_kmp.NativeToastTypeJs

actual object NativeShowToastCMP {
    actual fun show(
        msg: String,
        nativeToastType: NativeToastTypeCMP
    ) {
        NativeShowToastJs.show(
            msg,
            nativeToastType.toNativeToastType()
        )
    }


}

fun NativeToastTypeCMP.toNativeToastType(): NativeToastTypeJs {
    return when (this) { // Use 'this' to refer to the instance of NativeToastTypeCMP
        NativeToastTypeCMP.LONG -> NativeToastTypeJs.LONG
        NativeToastTypeCMP.SHORT -> NativeToastTypeJs.SHORT
    }
}