package com.example.credit_score.ui.credit_score_hub

import androidx.annotation.StringRes
import com.example.credit_score_data.domain.model.CreditScoreData

data class CreditScoreHubState(
    val creditScoreLoadingState: CreditScoreLoadingState = CreditScoreLoadingState.Loading
)

sealed class CreditScoreLoadingState {
    object Loading : CreditScoreLoadingState()
    data class Error(
        @StringRes val title: Int,
        @StringRes val description: Int
    ) : CreditScoreLoadingState()

    data class Success(val creditScoreData: CreditScoreData) : CreditScoreLoadingState()
}