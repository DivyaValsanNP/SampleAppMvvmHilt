package com.example.sampleappmvvmhilt.data.api

import com.example.sampleappmvvmhilt.data.entity.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsApiService {

    @GET("v2/top-headlines")
    suspend fun getNewsApiData(
      @Query("country") country : String,
      @Query("apiKey") apiKey : String = "eb9aefc91b6d471ea65853e0f8ce1d0b"
    ) : Response<NewsResponse>
}

//https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=eb9aefc91b6d471ea65853e0f8ce1d0b