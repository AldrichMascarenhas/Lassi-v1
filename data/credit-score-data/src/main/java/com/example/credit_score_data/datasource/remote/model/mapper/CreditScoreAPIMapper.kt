package com.example.credit_score_data.datasource.remote.model.mapper

import com.example.credit_score_data.datasource.remote.model.CreditData
import com.example.credit_score_data.domain.model.CreditScoreData

internal class CreditScoreAPIMapper {

    fun map(creditData: CreditData): CreditScoreData {
        return CreditScoreData(
            score = creditData.creditReportInfo.score,
            maxScoreValue = creditData.creditReportInfo.maxScoreValue

        )
    }
}