package com.example.credit_score_data.datasource.remote

import com.example.common_data.networking.retrofit.retrofitApiCall
import com.example.credit_score_data.datasource.CreditScoreDataSource
import com.example.credit_score_data.datasource.remote.model.mapper.CreditScoreAPIMapper
import com.example.credit_score_data.domain.response.GetCreditScoreResponse

internal class CreditScoreDataSourceImpl(
    private val creditScoreAPIService: CreditScoreAPIService,
    private val creditScoreAPIMapper : CreditScoreAPIMapper
) : CreditScoreDataSource {

    override suspend fun getCreditScore(): GetCreditScoreResponse {
        return retrofitApiCall(
            call = { creditScoreAPIService.getCreditScoreData() },
            success = {
                GetCreditScoreResponse.Success(creditScoreAPIMapper.map(it))
            },
            failure = { _, _ -> GetCreditScoreResponse.ServerError },
            noInternet = { GetCreditScoreResponse.NoInternet }
        )
    }

}