package com.example.sampleappmvvmhilt.data.datasource

import com.example.sampleappmvvmhilt.data.api.NewsApiService
import com.example.sampleappmvvmhilt.data.entity.NewsResponse
import retrofit2.Response
import javax.inject.Inject

class NewDataSourceImpl @Inject constructor(private val apiService: NewsApiService) :
    NewsDataSource {
    override suspend fun getNewsHeadLine(country: String): Response<NewsResponse> {
        return apiService.getNewsApiData(country)
    }

}