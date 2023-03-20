package com.example.networkdemo.example

import com.example.networkdemo.example.model.dto.ArticleDto
import com.example.networkdemo.example.model.mapper.toListArticleUI
import com.example.networkdemo.example.model.ui.ArticleUI
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.ApiSuccessModelMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
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

    override suspend fun fetchArticle2(): Flow<MyResult<List<ArticleUI>>> = flow {
        try {
            emit(MyResult.Loading(true))
            val list = exampleApi.fetchArticle2().toListArticleUI()
            emit(MyResult.Success(data = list))
        } catch (e: Throwable) {
            Timber.d(e.stackTraceToString())
            emit(MyResult.Failure(throwable = e))
        } finally {
            emit(MyResult.Loading(false))
        }
    }.flowOn(Dispatchers.IO)
}

object SuccessPosterMapper : ApiSuccessModelMapper<ArticleDto, List<ArticleUI>> {


    override fun map(apiSuccessResponse: ApiResponse.Success<ArticleDto>): List<ArticleUI> {
         return apiSuccessResponse.data.data.datas.map {
             ArticleUI(title = it.title, author = it.author)
         }
    }
}
