package com.example.networkdemo.example.model.mapper

import com.example.networkdemo.example.model.dto.ArticleDto
import com.example.networkdemo.example.model.ui.ArticleUI

fun ArticleDto.toListArticleUI(): List<ArticleUI> {
    return this.data.datas.map {
        ArticleUI(title = it.title, author = it.author)
    }
}