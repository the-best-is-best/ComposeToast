package io.github.tbib.compose_toast.native_toast

import android.widget.Toast
import io.github.tbib.compose_toast.applicationContext


actual object NativeShowToast {
    actual fun show(
        msg: String,
        type: NativeToastType
    ) {
        val duration = if (type == NativeToastType.SHORT) Toast.LENGTH_SHORT else Toast.LENGTH_LONG
        Toast.makeText(applicationContext, msg, duration).show()
    }
}