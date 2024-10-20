package io.github.tbib.compose_toast.native_toast


expect object NativeShowToast {
    /**
     * Displays a toast message on the screen.
     *
     * This function is intended for Android and iOS platforms only.
     *
     * @param msg The message to be displayed in the toast.
     * @param type The duration of the toast notification, which can be either:
     *             - [NativeToastType.LONG] for a longer display time,
     *             - [NativeToastType.SHORT] for a shorter display time.
     **/
    fun show(msg: String, type: NativeToastType)
}