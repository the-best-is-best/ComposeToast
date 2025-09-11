package io.github.tbib.compose_toast.native_toast

import kotlinx.browser.document
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.w3c.dom.HTMLElement

object WebShowToast {
    private val scope = MainScope()

    // تحويل لون Long إلى CSS rgba أو HEX
    private fun longToCssColor(color: Long): String {
        val a = ((color shr 24) and 0xFF) / 255.0
        val r = ((color shr 16) and 0xFF)
        val g = ((color shr 8) and 0xFF)
        val b = (color and 0xFF)

        return if (a > 0.0) {
            "rgba($r,$g,$b,$a)"
        } else {
            "#${r.toString(16).padStart(2, '0')}" +
                    g.toString(16).padStart(2, '0') +
                    b.toString(16).padStart(2, '0')
        }
    }

    fun show(
        msg: String,
        type: NativeToastType,
        backgroundColor: Long = 0x000000
    ) {
        val body = document.body ?: return

        // Container
        val containerId = "toast-container"
        var container = document.getElementById(containerId) as? HTMLElement
        if (container == null) {
            container = document.createElement("div") as HTMLElement
            container.id = containerId
            container.setAttribute(
                "style",
                """
                background-color: ${longToCssColor(backgroundColor)};
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

        // Toast
        val toast = document.createElement("div") as HTMLElement
        toast.textContent = msg
        toast.setAttribute(
            "style",
            """
            background-color: ${longToCssColor(backgroundColor)};
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

        // Auto remove
        scope.launch {
            delay(3000)
            toast.style.opacity = "0"
            delay(500)
            container.removeChild(toast)
        }
    }
}
