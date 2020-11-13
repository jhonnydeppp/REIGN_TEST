package com.reign.reigntest.common.api

import com.reign.reigntest.common.models.ArticlesResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ArticleRestApi {

    @Headers("Content-Type:application/json", "Accept:application/json")
    @GET("search_by_date")
    fun getArticles(@Query("query")  query: String = "android"): Observable<List<ArticlesResponse>>
}