package com.pdtrung.news.data.set

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PagedList
import com.pdtrung.news.api.NewsListModel
import com.pdtrung.news.data.dao.NewsDao
import kotlinx.coroutines.CoroutineScope

class NewsPageDataSourceFactory constructor(
    private val dataSource: NewsRemoteDataSource,
    private val dao: NewsDao,
    private val scope: CoroutineScope
) : DataSource.Factory<Int, NewsListModel>() {

    val liveData = MutableLiveData<NewsPageDataSource>()

    override fun create(): DataSource<Int, NewsListModel> {
        val source = NewsPageDataSource(dataSource, dao, scope)
        liveData.postValue(source)
        return source
    }

    companion object {
        private const val PAGE_SIZE = 20
        fun pagedListConfig() = PagedList.Config.Builder()
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(true)
            .build()
    }
}