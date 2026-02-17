package io.github.the_best_is_best.toast_kmp

import android.widget.Toast

enum class NativeToastType {
    SHORT, LONG
}

object NativeShowToast {
    fun show(
        msg: String,
        type: NativeToastType
    ) {
        val duration = if (type == NativeToastType.SHORT) Toast.LENGTH_SHORT else Toast.LENGTH_LONG
        Toast.makeText(applicationContext, msg, duration).show()
    }
}