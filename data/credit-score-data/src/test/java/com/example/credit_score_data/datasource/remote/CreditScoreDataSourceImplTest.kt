package com.example.credit_score_data.datasource.remote


import com.example.credit_score_data.datasource.util.MockWebServerUtils
import com.example.credit_score_data.datasource.CreditScoreDataSource
import com.example.credit_score_data.datasource.remote.model.mapper.CreditScoreAPIMapper
import com.example.credit_score_data.domain.model.CreditScoreData
import com.example.credit_score_data.domain.response.GetCreditScoreResponse
import io.mockk.*
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CreditScoreDataSourceImplTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var service: CreditScoreAPIService

    private val creditScoreAPIMapper = mockk<CreditScoreAPIMapper>()

    private lateinit var creditScoreDataSource: CreditScoreDataSource

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(CreditScoreAPIService::class.java)

        creditScoreDataSource = CreditScoreDataSourceImpl(service, creditScoreAPIMapper)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
        clearAllMocks()
    }

    @Test
    fun `given a server error when getting credit score then return a ServerError`() = runBlocking {
        mockWebServer.enqueue(
            MockWebServerUtils.getMockedHttpResponse(
                "credit-score-endpoint-response.json",
                404
            )
        )

        val actual = creditScoreDataSource.getCreditScore()

        Assert.assertEquals(GetCreditScoreResponse.ServerError, actual)
    }

    @Test
    fun `given a no internet error when getting credit score then return NoInternet`() =
        runBlocking {
            mockWebServer.shutdown()

            val actual = creditScoreDataSource.getCreditScore()

            Assert.assertEquals(GetCreditScoreResponse.NoInternet, actual)
        }

    @Test
    fun `given successwhen getting credit score then return Success`() =
        runBlocking {
            every { creditScoreAPIMapper.map(any()) } returns CreditScoreData(
                score = 100, maxScoreValue = 800,
            )
            mockWebServer.enqueue(
                MockWebServerUtils.getMockedHttpResponse(
                    "credit-score-endpoint-response.json",
                    200
                )
            )

            val actual = creditScoreDataSource.getCreditScore()

            Assert.assertEquals(
                GetCreditScoreResponse.Success(
                    creditScoreData = CreditScoreData(
                        score = 100,
                        maxScoreValue = 800
                    )
                ), actual
            )
        }
}