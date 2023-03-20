package com.example.networkdemo.example

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.networkdemo.example.model.dto.ArticleDto
import com.example.networkdemo.example.model.ui.ArticleUI

@Composable
fun ExampleScreen(viewModel: ExampleViewModel = viewModel()) {

    val article = viewModel.articleUIFlow.collectAsStateWithLifecycle()

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Text(text = "数据", fontWeight = FontWeight.Bold, fontSize = 24.sp)
        Spacer(modifier = Modifier.height(10.dp))
        article.value.forEach { 
            Article(articleUI = it, modifier = Modifier.fillMaxWidth())
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {
            viewModel.fetchArticle()
        }) {
            Text(text = "请求后台")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {
            viewModel.fetchArticleFlow()
        }) {
            Text(text = "请求后台2")
        }
    }
}

@Composable
fun Article(articleUI: ArticleUI, modifier: Modifier = Modifier) {

    Row(modifier = modifier) {
        Text(text = articleUI.author)
        Spacer(modifier = Modifier.width(20.dp))
        Text(text = articleUI.title)
    }

}