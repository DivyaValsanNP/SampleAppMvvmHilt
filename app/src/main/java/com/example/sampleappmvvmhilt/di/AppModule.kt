package com.example.sampleappmvvmhilt.di

import android.content.Context
import androidx.room.Room
import com.example.sampleappmvvmhilt.data.AppConstants
import com.example.sampleappmvvmhilt.data.api.NewsApiService
import com.example.sampleappmvvmhilt.data.datasource.NewDataSourceImpl
import com.example.sampleappmvvmhilt.data.datasource.NewsDataSource
import com.example.sampleappmvvmhilt.data.repository.NewsRepository
import com.example.sampleappmvvmhilt.utils.AppHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }

        val httpClient = OkHttpClient.Builder().apply {
            addInterceptor(httpLoggingInterceptor)
        }
        httpClient.apply {
            readTimeout(60, TimeUnit.SECONDS)
        }
        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): NewsApiService {
        return retrofit.create(NewsApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsDataSource(apiService: NewsApiService): NewsDataSource {
        return NewDataSourceImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(newsDataSource: NewsDataSource): NewsRepository {
        return NewsRepository(newsDataSource)
    }

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideToastHelper(@ApplicationContext context: Context): AppHelper {
        return AppHelper(context)
    }
}