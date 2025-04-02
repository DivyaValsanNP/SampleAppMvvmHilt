package com.example.sampleappmvvmhilt.data.datasource

import com.example.sampleappmvvmhilt.data.entity.NewsResponse
import retrofit2.Response

interface NewsDataSource {

    suspend fun getNewsHeadLine(country : String) : Response<NewsResponse>
}