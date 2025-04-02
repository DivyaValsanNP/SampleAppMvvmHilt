package com.example.sampleappmvvmhilt.ui.theme.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleappmvvmhilt.data.AppConstants
import com.example.sampleappmvvmhilt.data.entity.NewsResponse
import com.example.sampleappmvvmhilt.data.repository.NewsRepository
import com.example.sampleappmvvmhilt.utils.AppHelper
import com.example.sampleappmvvmhilt.utils.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val newsRepository: NewsRepository,val  appHelper: AppHelper) : ViewModel() {
    private val newsState: MutableStateFlow<ResourceState<NewsResponse>> =
        MutableStateFlow(ResourceState.Loading())

    val newsStateFlow: StateFlow<ResourceState<NewsResponse>> = newsState

    companion object {
        const val TAG = "NewsViewModel"
    }

    init {
        Log.d(TAG, ": Init block of NewViewModel is called")
        getNews(AppConstants.COUNTRY)
    }

    fun getNews(country: String) {
        viewModelScope.launch(Dispatchers.IO) {
            newsRepository.getNewsHeadLine(country).collectLatest { newsResponse ->
                newsState.value = newsResponse
            }
        }
    }

    fun showMyToast(message: String) {
        appHelper.showToast(message)
    }

    fun getTag(): String {
        return "Class_${this::class.java.simpleName}"
    }
}