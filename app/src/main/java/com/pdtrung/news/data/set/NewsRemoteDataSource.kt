package com.pdtrung.news.data.set

import com.pdtrung.news.api.NewsListResponse
import com.pdtrung.news.api.NewsService
import com.pdtrung.news.data.BaseDataSource
import com.pdtrung.news.data.Result
import com.pdtrung.news.util.KEYWORD_BITCOIN
import javax.inject.Inject

/* Works with the News API to get data. */
class NewsRemoteDataSource @Inject constructor(private val service: NewsService) : BaseDataSource() {

    suspend fun fetchNewsList(apiKey : String, page : Int, pageSize : Int ) : Result<NewsListResponse> {
        return getResult { service.getTopNewsList(apiKey, page,pageSize, KEYWORD_BITCOIN) }
    }
}