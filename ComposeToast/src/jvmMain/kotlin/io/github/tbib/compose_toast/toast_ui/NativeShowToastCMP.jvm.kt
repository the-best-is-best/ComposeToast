package io.github.tbib.compose_toast.toast_ui

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toAwtImage
import io.github.tbib.compose_toast.getAppLogo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.swing.Swing
import java.awt.Color
import java.awt.FlowLayout
import java.awt.Toolkit
import javax.swing.BorderFactory
import javax.swing.ImageIcon
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.WindowConstants

actual object NativeShowToastCMP {
    private var toastJob: Job? = null
    private var currentFrame: JFrame? = null
    actual fun show(
        msg: String,
        nativeToastType: NativeToastTypeCMP
    ) {
        toastJob?.cancel()
        currentFrame?.dispose()

        val logo = getAppLogo()
        val duration = if (nativeToastType == NativeToastTypeCMP.SHORT) 2000L else 3500L

        CoroutineScope(Dispatchers.Swing).launch {
            val frame = createToastFrame(msg, logo)
            currentFrame = frame
            frame.isVisible = true

            // Fade-in animation
            for (i in 0..10) {
                frame.opacity = i * 0.1f
                delay(20)
            }

            delay(duration)

            // Fade-out animation
            for (i in 10 downTo 0) {
                frame.opacity = i * 0.1f
                delay(20)
            }

            frame.dispose()
            if (currentFrame == frame) {
                currentFrame = null
            }
        }.also { toastJob = it }
    }

    private fun createToastFrame(msg: String, logo: ImageBitmap?): JFrame {
        return JFrame().apply {
            isUndecorated = true
            defaultCloseOperation = WindowConstants.DISPOSE_ON_CLOSE
            background = Color(0, 0, 0, 0)
            opacity = 0f

            contentPane = JPanel().apply {
                layout = FlowLayout(FlowLayout.CENTER, 15, 15)
                background = Color(60, 60, 60)
                border = BorderFactory.createEmptyBorder(15, 25, 15, 25)

                logo?.let {
                    // Create properly sized logo (24x24)
                    val scaledLogo =
                        it.toAwtImage().getScaledInstance(24, 24, java.awt.Image.SCALE_SMOOTH)
                    add(JLabel(ImageIcon(scaledLogo)).apply {
                        border = BorderFactory.createEmptyBorder(0, 0, 0, 10)
                    })
                }

                add(JLabel("<html><div style='text-align: center;'>$msg</div></html>").apply {
                    foreground = Color.WHITE
                    font = font.deriveFont(14f)
                })
            }

            pack()

            // Calculate position (bottom center with 20px margin from bottom)
            val screenSize = Toolkit.getDefaultToolkit().screenSize
            val screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(graphicsConfiguration)
            val bottomInset = screenInsets.bottom

            setLocation(
                (screenSize.width - width) / 2,
                screenSize.height - height - bottomInset - 20 // 20px margin above taskbar
            )

            // Make rounded corners
            try {
                val roundedCorners = Class.forName("java.awt.geom.RoundRectangle2D\$Double")
                val shape = roundedCorners.getConstructor(
                    Double::class.javaPrimitiveType,
                    Double::class.javaPrimitiveType,
                    Double::class.javaPrimitiveType,
                    Double::class.javaPrimitiveType,
                    Double::class.javaPrimitiveType,
                    Double::class.javaPrimitiveType
                ).newInstance(
                    0.0, 0.0,
                    width.toDouble(), height.toDouble(),
                    20.0, 20.0
                )
                this::class.java.getMethod("setShape", Class.forName("java.awt.Shape"))
                    .invoke(this, shape)
            } catch (e: Exception) {
                // Fallback if rounded corners not supported
                println("Rounded corners not supported: ${e.message}")
            }
        }

    }
}