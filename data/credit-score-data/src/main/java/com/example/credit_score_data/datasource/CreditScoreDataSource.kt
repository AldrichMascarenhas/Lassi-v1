package com.example.credit_score_data.datasource

import com.example.credit_score_data.domain.response.GetCreditScoreResponse

internal interface CreditScoreDataSource {
   suspend fun getCreditScore() : GetCreditScoreResponse
}