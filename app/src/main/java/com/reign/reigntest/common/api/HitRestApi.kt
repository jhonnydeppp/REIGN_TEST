package com.reign.reigntest.common.api

import com.reign.reigntest.common.models.HitsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface HitRestApi {

    @Headers("Content-Type:application/json", "Accept:application/json")
    @GET("search_by_date")
    fun getHits(@Query("query")  query: String = "android"): Observable<HitsResponse>
}