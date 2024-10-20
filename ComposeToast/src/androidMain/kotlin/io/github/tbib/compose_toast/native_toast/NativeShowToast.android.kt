package io.github.tbib.compose_toast.native_toast

import android.app.Activity
import android.widget.Toast
import java.lang.ref.WeakReference


object AndroidNativeShowToast {
    private var activity: WeakReference<Activity?> = WeakReference(null)

    internal fun getActivity(): Activity {
        return activity.get()!!
    }

    fun initialization(activity: Activity) {
        this.activity = WeakReference(activity)
    }

}

actual object NativeShowToast {
    actual fun show(
        msg: String,
        type: NativeToastType
    ) {
        val duration = if (type == NativeToastType.SHORT) Toast.LENGTH_SHORT else Toast.LENGTH_LONG
        Toast.makeText(AndroidNativeShowToast.getActivity(), msg, duration).show()
    }
}