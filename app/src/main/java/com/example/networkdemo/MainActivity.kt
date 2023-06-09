package com.example.networkdemo

import android.os.Bundle
import android.widget.Space
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.networkdemo.ui.theme.NetworkDemoTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.drawablepainter.rememberDrawablePainter

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
    val qrcode = viewModel.qrCode.collectAsStateWithLifecycle()
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
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {
            viewModel.getConfigCode(
                doOnSuccess = {
                    Toast.makeText(context, "成功获取", Toast.LENGTH_SHORT).show()
                },
                doOnFailure = {
                    Toast.makeText(context, "失败，看log", Toast.LENGTH_SHORT).show()
                }
            )
        }) {
            Text(text = "获取推流配置码")
        }

        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {
            viewModel.checkConfigCode(
                doOnSuccess = {
                    Toast.makeText(context, "成功获取", Toast.LENGTH_SHORT).show()
                },
                doOnFailure = {
                    Toast.makeText(context, "失败，看log", Toast.LENGTH_SHORT).show()
                }
            )
        }) {
            Text(text = "检查推流配置码")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Image(
            painter = rememberDrawablePainter(drawable = qrcode.value),
            contentDescription = "content description",
        )
    }
}