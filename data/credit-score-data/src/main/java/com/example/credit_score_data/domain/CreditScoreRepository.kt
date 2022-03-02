package com.example.credit_score_data.domain

import com.example.credit_score_data.domain.response.GetCreditScoreResponse

interface CreditScoreRepository {

    suspend fun getCreditScore(): GetCreditScoreResponse

}