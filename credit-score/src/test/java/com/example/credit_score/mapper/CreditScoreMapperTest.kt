package com.example.credit_score.mapper

import android.content.Context
import com.example.credit_score.ui.data.mockCreditScoreData
import com.example.credit_score.ui.data.mockCreditScoreHubData
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase
import org.junit.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CreditScoreMapperTest {

    private val context = mockk<Context>()

    lateinit var creditScoreMapper: CreditScoreMapper

    @Before
    fun setUp() {
        creditScoreMapper = CreditScoreMapper(context)
        every { context.getString(any()) } returns "test-string"
        every { context.getString(any(), any()) } returns "test-string"

    }

    @Test
    fun `mapping test`() {
        val expected = mockCreditScoreHubData.copy(title = "test-string", subtitle = "test-string")
        val actual = creditScoreMapper.mapCreditScoreData(mockCreditScoreData)
        assertEquals(actual, expected)
    }


}