package io.github.the_best_is_best.toast_kmp

import kotlinx.browser.document
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.w3c.dom.HTMLElement
import org.w3c.dom.get

@OptIn(ExperimentalJsExport::class)
@JsExport
class WebShowToast {
    private val scope = MainScope()

    /**
     * @param msg The text to show
     * @param backgroundColor Background color of the toast (default black)
     * @param textColor Text color (default white)
     * @param position Toast position: "bottom-right" | "bottom-left" | "top-right" | "top-left" (default "bottom-right")
     */
    fun show(
        msg: String,
        backgroundColor: String = "#000000",
        textColor: String = "#ffffff",
        position: String = "bottom-right"
    ) {
        val body = document.body ?: return

        val containerId = "toast-container"
        var container = document.getElementById(containerId) as? HTMLElement
        if (container == null) {
            container = document.createElement("div") as HTMLElement
            container.id = containerId
            container.style.position = "fixed"
            container.style.display = "flex"
            container.style.flexDirection = "column"
            container.style.zIndex = "9999"
            body.appendChild(container)
        }

        // ضبط موقع الـ container حسب position
        when (position) {
            "bottom-right" -> {
                container.style.bottom = "20px"
                container.style.right = "20px"
                container.style.top = ""
                container.style.left = ""
            }

            "bottom-left" -> {
                container.style.bottom = "20px"
                container.style.left = "20px"
                container.style.top = ""
                container.style.right = ""
            }

            "top-right" -> {
                container.style.top = "20px"
                container.style.right = "20px"
                container.style.bottom = ""
                container.style.left = ""
            }

            "top-left" -> {
                container.style.top = "20px"
                container.style.left = "20px"
                container.style.bottom = ""
                container.style.right = ""
            }

            else -> { // افتراضي
                container.style.bottom = "20px"
                container.style.right = "20px"
                container.style.top = ""
                container.style.left = ""
            }
        }

        // إزالة أي toast موجود مسبقًا
        val existingToasts = container.querySelectorAll("div")
        for (i in 0 until existingToasts.length) {
            container.removeChild(existingToasts[i] as HTMLElement)
        }

        val toast = document.createElement("div") as HTMLElement
        toast.textContent = msg
        toast.style.backgroundColor = backgroundColor
        toast.style.color = textColor
        toast.style.padding = "10px 16px"
        toast.style.borderRadius = "8px"
        toast.style.boxShadow = "0 2px 6px rgba(0,0,0,0.2)"
        toast.style.fontSize = "14px"
        toast.style.opacity = "1"
        toast.style.transition = "opacity 0.5s ease"

        container.appendChild(toast)

        scope.launch {
            delay(3000)
            toast.style.opacity = "0"
            delay(500)
            container.removeChild(toast)
        }
    }
}
