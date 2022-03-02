package com.example.credit_score.ui.data

import com.example.credit_score.usecase.GetCreditScoreResult
import com.example.credit_score_data.domain.model.CreditScoreData

const val CREDIT_SCORE = 123
const val CREDIT_SCORE_MAX_VALUE = 123

val mockGetCreditScoreResultSuccess = GetCreditScoreResult.Success(
    creditScoreData = CreditScoreData(
        score = CREDIT_SCORE,
        maxScoreValue = CREDIT_SCORE_MAX_VALUE
    )

)

val mockGetCreditScoreResultNoInternet = GetCreditScoreResult.NoInternet
val mockGetCreditScoreResultServerError = GetCreditScoreResult.ServerError

