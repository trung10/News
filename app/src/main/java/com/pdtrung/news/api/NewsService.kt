package com.pdtrung.news.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("/v2/everything")
    suspend fun getTopNewsList(
        @Query("apiKey") apiKey: String? = null, @Query("page") page: Int? = null,
        @Query("pageSize") pageSize: Int? = null,
        @Query("q") source: String? = null
    ): Response<NewsListResponse>

    companion object {
        const val ENDPOINT = "https://newsapi.org/"
    }
}