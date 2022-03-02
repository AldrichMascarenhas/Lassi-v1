package com.example.credit_score_data.di

import com.example.credit_score_data.datasource.CreditScoreDataSource
import com.example.credit_score_data.datasource.remote.CreditScoreAPIService
import com.example.credit_score_data.datasource.remote.CreditScoreDataSourceImpl
import com.example.credit_score_data.datasource.remote.model.mapper.CreditScoreAPIMapper
import com.example.credit_score_data.domain.CreditScoreRepository
import com.example.credit_score_data.domain.repository.CreditScoreRepositoryImpl
import org.koin.dsl.module
import retrofit2.Retrofit

val credit_score_data_module = module{
    single<CreditScoreAPIService> { createRetrofitService(get()) }
    single<CreditScoreRepository> { CreditScoreRepositoryImpl(get()) }
    single<CreditScoreDataSource> { CreditScoreDataSourceImpl(get(), get()) }

    factory { CreditScoreAPIMapper() }

}


inline fun <reified T> createRetrofitService(retrofit: Retrofit): T {
    return retrofit.create(T::class.java)
}