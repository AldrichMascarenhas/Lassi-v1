package com.example.credit_score_data.datasource.remote

import com.example.credit_score_data.datasource.remote.model.CreditData
import retrofit2.Response
import retrofit2.http.GET

internal interface CreditScoreAPIService {

    @GET("endpoint.json")
    suspend fun getCreditScoreData(): Response<CreditData>
}