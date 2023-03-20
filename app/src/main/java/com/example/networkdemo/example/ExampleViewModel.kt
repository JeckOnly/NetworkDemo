package com.example.networkdemo.example

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.networkdemo.example.model.dto.ArticleDto
import com.example.networkdemo.example.model.ui.ArticleUI
import com.skydoves.sandwich.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ExampleViewModel @Inject constructor(
    private val repo: ExampleIRepo
) : ViewModel() {


    private val mMutableArticleUI: MutableStateFlow<List<ArticleUI>> = MutableStateFlow(emptyList())
    val articleUIFlow: StateFlow<List<ArticleUI>> = mMutableArticleUI


    fun fetchArticle() {
        viewModelScope.launch {
            repo.fetchArticle().suspendOnSuccess {
                mMutableArticleUI.update {
                    data.data.datas.map {
                        ArticleUI(title = it.title, author = it.author)
                    }
                }
            }.suspendOnError {
                Timber.d(message = "Server error: " + message())
            }.suspendOnException {
                Timber.d(message = "Client error: " + message())
            }
        }
    }

    fun fetchArticleFlow() {
        viewModelScope.launch {
            repo.fetchArticle2().collect { result ->
                when(result) {
                    is MyResult.Failure -> {
                        // TODO
                    }
                    is MyResult.Loading -> {
                        // TODO
                    }
                    is MyResult.Success -> {
                        mMutableArticleUI.update {
                            result.data?: emptyList()
                        }
                    }
                }
            }
        }
    }

    @Deprecated("没有Adapter")
    fun fetchArticleToResult() {
        viewModelScope.launch {
            repo.fetchArticleToResult().collect { result ->
                mMutableArticleUI.update {
                    result
                }
            }
        }
    }
}