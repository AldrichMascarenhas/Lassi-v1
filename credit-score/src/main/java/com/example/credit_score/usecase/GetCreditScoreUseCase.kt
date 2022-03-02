package com.example.credit_score.usecase

import com.example.credit_score_data.domain.model.CreditScoreData
import com.example.credit_score_data.domain.CreditScoreRepository
import com.example.credit_score_data.domain.response.GetCreditScoreResponse

class GetCreditScoreUseCase(
    private val creditScoreRepository: CreditScoreRepository
) {
    suspend operator fun invoke(): GetCreditScoreResult {
        return when (val creditScoreResponse = creditScoreRepository.getCreditScore()) {
            GetCreditScoreResponse.NoInternet -> GetCreditScoreResult.NoInternet
            GetCreditScoreResponse.ServerError -> GetCreditScoreResult.ServerError
            is GetCreditScoreResponse.Success -> {
                GetCreditScoreResult.Success(creditScoreResponse.creditScoreData)
            }
        }
    }
}

sealed class GetCreditScoreResult {
    data class Success(val creditScoreData: CreditScoreData) : GetCreditScoreResult()
    object NoInternet : GetCreditScoreResult()
    object ServerError : GetCreditScoreResult()
}