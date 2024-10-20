package io.github.tbib.compose_toast.native_toast

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

actual object NativeShowToast {
    private var toastJob: Job? = null
    private var toastMessage: String? = null
    private var isShowing: Boolean = false
    actual fun show(
        msg: String,
        type: NativeToastType
    ) {
        toastMessage = msg
        isShowing = true

        // Cancel any existing job before starting a new one
        toastJob?.cancel()

        // Define the duration based on toast type
        val duration = when (type) {
            NativeToastType.SHORT -> 2000L // 2 seconds
            NativeToastType.LONG -> 3500L // 3.5 seconds
        }

        // Launch a coroutine to clear the message after the duration
        toastJob = CoroutineScope(Dispatchers.Main).launch {
            delay(duration)
            isShowing = false
            toastMessage = null // Clear the message
        }
    }
}