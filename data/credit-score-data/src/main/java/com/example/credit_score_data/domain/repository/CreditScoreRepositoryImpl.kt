package com.example.credit_score_data.domain.repository

import com.example.credit_score_data.datasource.CreditScoreDataSource
import com.example.credit_score_data.domain.response.GetCreditScoreResponse

internal class CreditScoreRepositoryImpl(
    private val creditScoreDataSource: CreditScoreDataSource
) : CreditScoreRepository {

    override suspend fun getCreditScore(): GetCreditScoreResponse {
        return creditScoreDataSource.getCreditScore()
    }
}