package com.example.credit_score_data.datasource.remote.model.mapper

import com.example.credit_score_data.datasource.mock.mockCreditData
import com.example.credit_score_data.datasource.mock.mockCreditReportInfo
import com.example.credit_score_data.domain.model.CreditScoreData
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CreditScoreAPIMapperTest {

    private lateinit var creditScoreAPIMapper: CreditScoreAPIMapper

    @Before
    fun setUp() {
        creditScoreAPIMapper = CreditScoreAPIMapper()
    }

    @Test
    fun `test successful mapping`(){
        val mockCreditData = mockCreditData.copy(
            creditReportInfo = mockCreditReportInfo.copy(
                score = 100,
                maxScoreValue = 800,
            )
        )
        val expected = CreditScoreData(
            score = 100, maxScoreValue = 800
        )
        val actual = creditScoreAPIMapper.map(mockCreditData)
        Assert.assertEquals(expected, actual)

    }

}