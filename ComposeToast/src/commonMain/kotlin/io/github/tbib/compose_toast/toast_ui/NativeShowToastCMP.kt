package io.github.tbib.compose_toast.toast_ui

enum class NativeToastTypeCMP {
    SHORT, LONG
}

expect object NativeShowToastCMP {
    fun show(
        msg: String,
        nativeToastType: NativeToastTypeCMP
    )
}
