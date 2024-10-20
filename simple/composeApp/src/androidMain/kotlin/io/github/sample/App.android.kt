package io.github.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class AppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        io.github.tbib.compose_toast.native_toast.AndroidNativeShowToast.initialization(this)
        setContent { App() }
    }
}

@Preview
@Composable
fun AppPreview() {
    App()
}
