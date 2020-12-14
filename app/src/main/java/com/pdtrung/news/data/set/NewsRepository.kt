package com.pdtrung.news.data.set

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import com.pdtrung.news.api.Data
import com.pdtrung.news.api.NetworkState
import com.pdtrung.news.api.NewsListModel
import com.pdtrung.news.data.dao.NewsDao
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NewsRepository @Inject constructor(
    private val newsDao: NewsDao,
    private val newsRemoteDataSource: NewsRemoteDataSource
) {

    fun observePagedNews(connectivityAvailable: Boolean, coroutineScope: CoroutineScope)
            : Data<NewsListModel> {

        return if (connectivityAvailable)
            observeRemotePagedNews(coroutineScope)
        else observeLocalPagedNews()
    }

    // data set from local (Database)
    private fun observeLocalPagedNews(): Data<NewsListModel> {

        val dataSourceFactory = newsDao.getPagedNews()

        val createLD = MutableLiveData<NetworkState>()
        createLD.postValue(NetworkState.LOADED)

        return Data(
            LivePagedListBuilder(
                dataSourceFactory,
                NewsPageDataSourceFactory.pagedListConfig()
            ).build(), createLD
        )
    }

    // data set from api
    private fun observeRemotePagedNews(ioCoroutineScope: CoroutineScope): Data<NewsListModel> {
        val dataSourceFactory = NewsPageDataSourceFactory(
            newsRemoteDataSource,
            newsDao, ioCoroutineScope
        )

        val networkState = Transformations.switchMap(dataSourceFactory.liveData) {
            it.networkState
        }
        return Data(
            LivePagedListBuilder(
                dataSourceFactory,
                NewsPageDataSourceFactory.pagedListConfig()
            ).build(), networkState
        )
    }
}