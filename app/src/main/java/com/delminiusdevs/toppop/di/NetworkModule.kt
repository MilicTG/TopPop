package com.delminiusdevs.toppop.di

import com.delminiusdevs.toppop.data.remote.TopPopApi
import com.delminiusdevs.toppop.data.repository.TopPopChartRepositoryImpl
import com.delminiusdevs.toppop.domain.repository.TopPopChartRepository
import com.delminiusdevs.toppop.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideTopPopApi(retrofit: Retrofit): TopPopApi{
        return retrofit.create(TopPopApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTopPopChartSource(
        topPopApi: TopPopApi
    ) : TopPopChartRepository {
        return TopPopChartRepositoryImpl(
            topPopApi = topPopApi
        )
    }
}