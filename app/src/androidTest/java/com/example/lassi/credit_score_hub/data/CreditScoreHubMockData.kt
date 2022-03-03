package com.example.lassi.credit_score_hub.data

import com.example.credit_score.model.CreditScoreHubData
import com.example.credit_score_data.domain.model.CreditScoreData

const val CREDIT_SCORE = 550
const val MAX_CREDIT_SCORE = 800
const val CREDIT_SCORE_HUB_INDICATOR_TITLE = "Your credit score is"
const val CREDIT_SCORE_HUB_INDICATOR_SUBTITLE = "out of $CREDIT_SCORE"
const val CREDIT_SCORE_HUB_INDICATOR_VALUE = "550"


val mockCreditScoreData = CreditScoreData(
    score = CREDIT_SCORE, maxScoreValue = MAX_CREDIT_SCORE
)

val mockCreditScoreHubData = CreditScoreHubData(
    score = CREDIT_SCORE,
    maxScoreValue = MAX_CREDIT_SCORE,
    title = CREDIT_SCORE_HUB_INDICATOR_TITLE,
    subtitle = CREDIT_SCORE_HUB_INDICATOR_SUBTITLE,
    value = CREDIT_SCORE_HUB_INDICATOR_VALUE
)

