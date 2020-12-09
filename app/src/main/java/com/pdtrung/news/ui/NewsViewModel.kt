package com.pdtrung.news.ui

import androidx.lifecycle.ViewModel
import com.pdtrung.news.api.Data
import com.pdtrung.news.api.NewsListModel
import com.pdtrung.news.data.set.NewsRepository
import com.pdtrung.news.di.CoroutineScopeIO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel

class NewsViewModel(
    private val repository: NewsRepository,
    @CoroutineScopeIO private val ioCoroutineScope: CoroutineScope
) : ViewModel() {
    private var newsList: Data<NewsListModel>? = null

    override fun onCleared() {
        super.onCleared()
        ioCoroutineScope.cancel()
    }

    fun newsList(connectivityAvailable: Boolean): Data<NewsListModel>? {

        if (newsList == null) {
            //newsList = repository.observePagedNews(connectivityAvailable, ioCoroutineScope)
        }
        return newsList
    }

}