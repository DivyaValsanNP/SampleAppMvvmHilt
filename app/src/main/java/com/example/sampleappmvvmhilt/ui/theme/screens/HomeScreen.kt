package com.example.sampleappmvvmhilt.ui.theme.screens

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sampleappmvvmhilt.ui.theme.components.Loader
import com.example.sampleappmvvmhilt.ui.theme.components.NewsList
import com.example.sampleappmvvmhilt.ui.theme.viewmodel.NewsViewModel
import com.example.sampleappmvvmhilt.utils.ResourceState


@Composable
fun HomeScreen(
    newsViewModel: NewsViewModel = hiltViewModel()
) {
    val newsResponse by newsViewModel.newsStateFlow.collectAsState()
    val pagerState = rememberPagerState(initialPage = 0, initialPageOffsetFraction = 0f) {
        100
    }
    val modifier = Modifier.fillMaxSize().padding(top = 20.dp)
    VerticalPager(
        state = pagerState,
        modifier = modifier,
        pageSize = PageSize.Fill,
        pageSpacing = 10.dp
    ) { page: Int ->
        when (newsResponse) {
            is ResourceState.Loading -> {
                Log.d(newsViewModel.getTag(), "HomeScreen: Loading ")
                Loader()
            }

            is ResourceState.Success -> {
                val response = (newsResponse as ResourceState.Success).data
                Log.d(newsViewModel.getTag(), "HomeScreen: Success ${response.status} = ${response.totalResults}")


                if(response.articles.isNotEmpty()) {
                    NewsList(response)
               }
            }

            is ResourceState.Error -> {
                Log.d(newsViewModel.getTag(), "HomeScreen: Error ")
            }
        }
    }
}