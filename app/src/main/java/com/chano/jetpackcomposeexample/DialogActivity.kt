package com.chano.jetpackcomposeexample

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.chano.jetpackcomposeexample.ui.theme.JetpackComposeExampleTheme

class DialogActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    var show by rememberSaveable() {
                        mutableStateOf(false)
                    }
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Button(onClick = { show = true }) {
                            Text(text = "Dialogo")
                        }
                    }
                    MyDialog(
                        show = show,
                        onDismiss = {
                            show = false
                            Toast.makeText(this, "Se cancelo el dialog", Toast.LENGTH_SHORT).show()
                        },
                        onConfirm = {
                            show = false
                            Toast.makeText(this, "Se confirmo el dialog", Toast.LENGTH_SHORT).show()
                        })
                }
            }
        }
    }
}

@Composable
fun MyDialog(
    show: Boolean,
    onDismiss:() -> Unit,
    onConfirm:() -> Unit
) {
    if (show) {
        AlertDialog(
            onDismissRequest = {
               onDismiss()
            },
            confirmButton = {
                TextButton(onClick = { onConfirm() }) {
                    Text(text = "Aceptar")
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text(text = "Cancelar")
                }
            },
            title = { Text(text = "Titulo") },
            text = { Text(text = "Esto es el body") }
        )
    }
}

@Composable
fun Greeting3(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    JetpackComposeExampleTheme {
        Greeting3("Android")
    }
}