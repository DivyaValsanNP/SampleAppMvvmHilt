package com.example.sampleappmvvmhilt.ui.theme.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sampleappmvvmhilt.ui.theme.components.NewsRowComponent
import com.example.sampleappmvvmhilt.ui.theme.viewmodel.NewsViewModel

@Composable
fun DescriptionScreen(
    newsViewModel: NewsViewModel = hiltViewModel()) {
    val newsResponse by newsViewModel.newsStateFlow.collectAsState()
}