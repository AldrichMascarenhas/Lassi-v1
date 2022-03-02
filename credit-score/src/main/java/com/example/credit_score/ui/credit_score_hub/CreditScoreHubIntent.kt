package com.example.credit_score.ui.credit_score_hub

sealed class CreditScoreHubIntent {
    object FetchCreditScore : CreditScoreHubIntent()
}
