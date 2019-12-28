package com.lalitpawar.shaadicomexample.network

import com.lalitpawar.shaadicomexample.model.BaseData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/api")
    fun getMatchList(@Query("results") page: String): Observable<BaseData>
}