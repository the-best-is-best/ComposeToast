package org.company.app.androidApp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.the_best_is_best.toast_kmp.NativeShowToast
import io.github.the_best_is_best.toast_kmp.NativeToastType

@Composable
fun NativeApp() = MaterialTheme {
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            ElevatedButton(onClick = {
                NativeShowToast.show(
                    "Native toast Native toast Native toast Native toast Native toast",
                    NativeToastType.LONG
                )
            }) {
                Text(text = "Show native Toast")
            }
        }

    }
}