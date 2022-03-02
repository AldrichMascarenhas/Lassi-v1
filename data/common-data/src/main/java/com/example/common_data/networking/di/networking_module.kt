package com.example.common_data.networking.di

import com.example.common_data.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networking_module = module {

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.NONE
        }
    }
    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }
    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(BuildConfig.BASE_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}