package com.example.sampleappmvvmhilt.data.entity

data class Article(
    val author: String ? = null,
    val content: String ? = null,
    val description: String ?= null,
    val publishedAt: String?= null,
    val source: Source,
    val title: String ? = null,
    val url: String ?= null,
    val urlToImage: String ?= null
)