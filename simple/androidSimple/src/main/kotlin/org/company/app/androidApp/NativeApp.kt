package org.company.app.androidApp

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import io.github.sample.App

@Composable
fun NativeApp() = MaterialTheme {
    Scaffold { paddingValues ->
//        Box(
//            modifier = Modifier
//                .padding(paddingValues)
//                .fillMaxSize()
//        ) {
//            ElevatedButton(onClick = {
//                NativeShowToast.show(
//                    "Native toast Native toast Native toast Native toast Native toast",
//                    NativeToastType.LONG
//                )
//            }) {
//                Text(text = "Show native Toast")
//            }
//        }
        App()

    }
}