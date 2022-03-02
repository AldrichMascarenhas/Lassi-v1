package com.example.credit_score_data.domain.repository

import com.example.credit_score_data.domain.response.GetCreditScoreResponse

interface CreditScoreRepository {

    suspend fun getCreditScore(): GetCreditScoreResponse
}