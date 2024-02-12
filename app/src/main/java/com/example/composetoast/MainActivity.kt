package com.example.composetoast

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composetoast.ui.theme.ComposeToastTheme
import io.tbib.composetoast.AdvToast
import io.tbib.composetoast.rememberAdvToastStates
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeToastTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val stateToast = rememberAdvToastStates()

    val coroutineScope = rememberCoroutineScope()
AdvToast.MakeErrorText(state = stateToast,modifier = modifier)

//    val context = LocalContext.current
//Toast.makeText(context, "Hello $name!", Toast.LENGTH_SHORT).show()
  Column {
        Text(text = "Hello $name!", modifier = modifier)
        ElevatedButton(onClick = {
            coroutineScope.launch {
                stateToast.show("Hello $name! Toast")
            }
        }) {
            Text(text = "Show Toast")
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeToastTheme {
        Greeting("Android")
    }
}