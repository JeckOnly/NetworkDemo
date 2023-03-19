package com.example.networkdemo.example

import com.example.networkdemo.example.model.dto.ArticleDto
import com.example.networkdemo.example.model.ui.ArticleUI
import com.skydoves.sandwich.*
import kotlinx.coroutines.flow.update
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExampleRepoImpl
@Inject constructor(
    private val exampleApi: ExampleApi
) : ExampleIRepo {
    override suspend fun fetchArticle(): ApiResponse<ArticleDto> {
       return exampleApi.fetchArticle()
    }
}

object SuccessPosterMapper : ApiSuccessModelMapper<ArticleDto, List<ArticleUI>> {


    override fun map(apiSuccessResponse: ApiResponse.Success<ArticleDto>): List<ArticleUI> {
         return apiSuccessResponse.data.data.datas.map {
             ArticleUI(title = it.title, author = it.author)
         }
    }
}
