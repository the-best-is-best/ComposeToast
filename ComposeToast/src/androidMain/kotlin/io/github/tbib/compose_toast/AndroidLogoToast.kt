package io.github.tbib.compose_toast

import android.app.Activity
import java.lang.ref.WeakReference

object AndroidLogoToast {
    private var activity: WeakReference<Activity?> = WeakReference(null)

    internal fun getActivity(): Activity {
        return activity.get()!!
    }

    fun initialization(activity: Activity) {
        this.activity = WeakReference(activity)
    }

}