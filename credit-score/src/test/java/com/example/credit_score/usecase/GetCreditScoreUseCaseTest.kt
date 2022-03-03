package com.example.credit_score.usecase

import com.example.credit_score.ui.data.mockCreditScoreData
import com.example.credit_score_data.domain.CreditScoreRepository
import com.example.credit_score_data.domain.response.GetCreditScoreResponse
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetCreditScoreUseCaseTest {

    private val creditScoreRepository = mockk<CreditScoreRepository>()

    lateinit var creditScoreUseCase: GetCreditScoreUseCase

    @Before
    fun setUp() {
        creditScoreUseCase = GetCreditScoreUseCase(creditScoreRepository)
    }

    @Test
    fun `given the request is successful when invoke is called then return Success`() =
        runBlocking {
            val expected = GetCreditScoreResult.Success(mockCreditScoreData)
            // GIVEN
            coEvery {
                creditScoreRepository.getCreditScore()
            } returns GetCreditScoreResponse.Success(mockCreditScoreData)

            // WHEN
            val actual = creditScoreUseCase.invoke()

            // THEN
            assertEquals(expected, actual)
        }


    @Test
    fun `given the request is a failure when invoke is called then return NoInternet`() =
        runBlocking {
            val expected = GetCreditScoreResult.NoInternet
            // GIVEN
            coEvery {
                creditScoreRepository.getCreditScore()
            } returns GetCreditScoreResponse.NoInternet

            // WHEN
            val actual = creditScoreUseCase.invoke()

            // THEN
            assertEquals(expected, actual)
        }


    @Test
    fun `given the request is a failure when invoke is called then return ServerError`() =
        runBlocking {
            val expected = GetCreditScoreResult.ServerError
            // GIVEN
            coEvery {
                creditScoreRepository.getCreditScore()
            } returns GetCreditScoreResponse.ServerError

            // WHEN
            val actual = creditScoreUseCase.invoke()

            // THEN
            assertEquals(expected, actual)
        }

}