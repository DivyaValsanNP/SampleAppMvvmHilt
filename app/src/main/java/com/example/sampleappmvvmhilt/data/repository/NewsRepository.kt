package com.example.sampleappmvvmhilt.data.repository

import com.example.sampleappmvvmhilt.data.datasource.NewsDataSource
import com.example.sampleappmvvmhilt.data.entity.NewsResponse
import com.example.sampleappmvvmhilt.utils.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsDataSource: NewsDataSource) {

    fun getNewsHeadLine(country: String) : Flow<ResourceState<NewsResponse>> {
        return flow {
            emit(ResourceState.Loading())

            val response = newsDataSource.getNewsHeadLine(country)

            if(response.isSuccessful && response != null) {
                emit(ResourceState.Success(response.body()!!))
            } else {
                emit(ResourceState.Error("Error fetching new Data"))
            }
        }.catch { e ->
            emit(ResourceState.Error(e.localizedMessage.toString()))
        }
    }
}