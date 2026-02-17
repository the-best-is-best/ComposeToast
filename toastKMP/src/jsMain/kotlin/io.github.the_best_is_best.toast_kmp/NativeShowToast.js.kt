package io.github.the_best_is_best.toast_kmp

import kotlinx.browser.document
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.w3c.dom.HTMLElement

@OptIn(ExperimentalJsExport::class)
@JsExport
enum class NativeToastTypeJs {
    SHORT, LONG
}

@OptIn(ExperimentalJsExport::class)
@JsExport
object NativeShowToastJs {
    private val scope = MainScope()

    fun show(
        msg: String,
        type: NativeToastTypeJs
    ) {
        val body = document.body ?: return

        // Create toast container if not exists
        val containerId = "toast-container"
        var container = document.getElementById(containerId) as? HTMLElement
        if (container == null) {
            container = document.createElement("div") as HTMLElement
            container.id = containerId
            container.setAttribute(
                "style",
                """
                position: fixed;
                bottom: 20px;
                right: 20px;
                display: flex;
                flex-direction: column;
                gap: 10px;
                z-index: 9999;
                """.trimIndent()
            )
            body.appendChild(container)
        }

        // Create toast element
        val toast = document.createElement("div") as HTMLElement
        toast.textContent = msg
        toast.setAttribute(
            "style",
            """
            background-color: rgba(0,0,0,0.7);
            color: white;
            padding: 10px 16px;
            border-radius: 8px;
            box-shadow: 0 2px 6px rgba(0,0,0,0.2);
            font-size: 14px;
            opacity: 1;
            transition: opacity 0.5s ease;
            """.trimIndent()
        )

        container.appendChild(toast)

        // Auto remove after 3s
        scope.launch {
            delay(3000)
            toast.style.opacity = "0"
            delay(500)
            container.removeChild(toast)
        }
    }

}
