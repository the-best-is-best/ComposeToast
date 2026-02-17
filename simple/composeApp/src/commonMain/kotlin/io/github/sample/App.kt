package io.github.sample

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.sample.theme.AppTheme
import io.github.tbib.compose_toast.AdvToast
import io.github.tbib.compose_toast.rememberAdvToastStates
import io.github.tbib.compose_toast.toast_ui.EnumToastType
import io.github.tbib.compose_toast.toast_ui.NativeShowToastCMP
import io.github.tbib.compose_toast.toast_ui.NativeToastTypeCMP
import kotlinx.coroutines.launch

@Composable
fun App() = AppTheme {
    val stateToast = rememberAdvToastStates()

    val stateCustomToast = rememberAdvToastStates()


    val coroutineScope = rememberCoroutineScope()
    Box(modifier = Modifier.fillMaxSize()) {
        AdvToast.MakeToast(state = stateToast, toastType = EnumToastType.INFO, paddingBottom = 50)
        AdvToast.MakeCustomToast(
            state = stateCustomToast,
            textColor = Color.Black,
            paddingTop = 50,
            modifier = Modifier.padding(horizontal = 50.dp)
                .background(Color.Cyan, shape = RoundedCornerShape(8.dp)),
            // backgroundColor = Color.Cyan,
            align = Arrangement.Top,
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.safeDrawing)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ElevatedButton(onClick = {
                coroutineScope.launch {
                    stateToast.show("Test toast")
                }
            }) {
                Text(text = "Show Toast")
            }

            Spacer(Modifier.height(10.dp))
            ElevatedButton(onClick = {
                coroutineScope.launch {
                    stateCustomToast.show("Test toast")
                }
            }) {
                Text(text = "Show custom Toast")
            }
            Spacer(Modifier.height(10.dp))
            ElevatedButton(onClick = {
                NativeShowToastCMP.show(
                    "Native toast Native toast Native toast Native toast Native toast",
                    NativeToastTypeCMP.LONG
                )
            }) {
                Text(text = "Show native Toast")
            }
        }
    }
}
