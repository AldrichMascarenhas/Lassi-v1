package com.example.credit_score_data.domain.response

import com.example.credit_score_data.domain.model.CreditScoreData

sealed class GetCreditScoreResponse {
    data class Success(val creditScoreData: CreditScoreData) : GetCreditScoreResponse()
    object NoInternet : GetCreditScoreResponse()
    object ServerError : GetCreditScoreResponse()
}


