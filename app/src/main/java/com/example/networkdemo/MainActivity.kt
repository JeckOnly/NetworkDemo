package com.example.networkdemo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.networkdemo.ui.theme.NetworkDemoTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.lifecycle.viewmodel.compose.viewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NetworkDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyScreen()
                }
            }
        }
    }
}

@Composable
fun MyScreen(
    viewModel: MainViewModel = viewModel()
) {
    val context = LocalContext.current
    Column {
        Button(onClick = {
            viewModel.getVeriKey(
                doOnSuccess = {
                    Toast.makeText(context, "成功获取", Toast.LENGTH_SHORT).show()
                },
                doOnFailure = {
                    Toast.makeText(context, "失败，看log", Toast.LENGTH_SHORT).show()
                }
            )
        }) {
            Text(text = "获取推流key")
        }
    }
}