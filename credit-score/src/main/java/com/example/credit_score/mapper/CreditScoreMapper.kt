package com.example.credit_score.mapper

import android.content.Context
import com.example.strings.R
import com.example.credit_score.model.CreditScoreHubData
import com.example.credit_score_data.domain.model.CreditScoreData

class CreditScoreMapper(
    private val context: Context
) {

    fun mapCreditScoreData(creditScoreData: CreditScoreData): CreditScoreHubData {
        return CreditScoreHubData(
            score = creditScoreData.score,
            maxScoreValue = creditScoreData.maxScoreValue,
            title = context.getString(R.string.credit_score_hub_widget_title),
            subtitle = context.getString(
                R.string.credit_score_hub_widget_subtitle,
                creditScoreData.maxScoreValue
            ),
            value = creditScoreData.score.toString()
        )
    }
}