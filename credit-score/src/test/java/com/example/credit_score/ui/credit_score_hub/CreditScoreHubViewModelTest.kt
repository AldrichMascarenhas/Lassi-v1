package com.example.credit_score.ui.credit_score_hub

import com.example.credit_score.mapper.CreditScoreMapper
import com.example.credit_score.ui.data.mockCreditScoreHubData
import com.example.credit_score.ui.data.mockGetCreditScoreResultNoInternet
import com.example.credit_score.ui.data.mockGetCreditScoreResultServerError
import com.example.credit_score.ui.data.mockGetCreditScoreResultSuccess
import com.example.credit_score.usecase.GetCreditScoreUseCase
import com.example.strings.R
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.orbitmvi.orbit.test

class CreditScoreHubViewModelTest {

    private val getCreditScoreUseCase = mockk<GetCreditScoreUseCase>()
    private val creditScoreMapper = mockk<CreditScoreMapper>()

    @Test
    fun `load credit data use case returns Success`() = runBlocking {

        val overviews = mockGetCreditScoreResultSuccess
        val initialState = CreditScoreHubState()

        // given we mock the repository
        coEvery { getCreditScoreUseCase.invoke() } returns overviews
        every { creditScoreMapper.mapCreditScoreData(any()) } returns mockCreditScoreHubData

        // when we observe details from the view model
        val viewModel = CreditScoreHubViewModel(getCreditScoreUseCase, creditScoreMapper).test(
            initialState = initialState,
        )
        viewModel.runOnCreate()

        viewModel.testIntent { onIntent(CreditScoreHubIntent.FetchCreditScore) }

        // then the view model loads the overviews
        viewModel.assert(initialState) {
            states(
                { copy(creditScoreLoadingState = CreditScoreLoadingState.Loading) },
                {
                    copy(
                        creditScoreLoadingState = CreditScoreLoadingState.Success(
                            mockCreditScoreHubData
                        )
                    )
                }
            )
        }

        // Verification
        coVerify(exactly = 1) { getCreditScoreUseCase.invoke() }
        coVerify(exactly = 1) { creditScoreMapper.mapCreditScoreData(any()) }
    }

    @Test
    fun `load credit data use case returns NoInternet`() = runBlocking {
        val overviews = mockGetCreditScoreResultNoInternet
        val initialState = CreditScoreHubState()

        // given we mock the repository
        coEvery { getCreditScoreUseCase.invoke() } returns overviews

        // when we observe details from the view model
        val viewModel = CreditScoreHubViewModel(getCreditScoreUseCase, creditScoreMapper).test(
            initialState = initialState,
        )
        viewModel.runOnCreate()

        viewModel.testIntent { onIntent(CreditScoreHubIntent.FetchCreditScore) }

        // then the view model loads the overviews
        viewModel.assert(initialState) {
            states(
                { copy(creditScoreLoadingState = CreditScoreLoadingState.Loading) },
                {
                    copy(
                        creditScoreLoadingState = CreditScoreLoadingState.Error(
                            title = R.string.no_internet, description = R.string.no_internet_body
                        )
                    )
                }
            )
        }

        // Verification
        coVerify(exactly = 1) { getCreditScoreUseCase.invoke() }
        coVerify(exactly = 0) { creditScoreMapper.mapCreditScoreData(any()) }
    }

    @Test
    fun `load credit data use case returns ServerError`() = runBlocking {
        val overviews = mockGetCreditScoreResultServerError
        val initialState = CreditScoreHubState()

        // given we mock the repository
        coEvery { getCreditScoreUseCase.invoke() } returns overviews

        // when we observe details from the view model
        val viewModel = CreditScoreHubViewModel(getCreditScoreUseCase, creditScoreMapper).test(
            initialState = initialState,
        )
        viewModel.runOnCreate()

        viewModel.testIntent { onIntent(CreditScoreHubIntent.FetchCreditScore) }

        // then the view model loads the overviews
        viewModel.assert(initialState) {
            states(
                { copy(creditScoreLoadingState = CreditScoreLoadingState.Loading) },
                {
                    copy(
                        creditScoreLoadingState = CreditScoreLoadingState.Error(
                            title = R.string.generic_error_title,
                            description = R.string.generic_error_subtitle
                        )
                    )
                }
            )
        }

        // Verification
        coVerify(exactly = 1) { getCreditScoreUseCase.invoke() }
        coVerify(exactly = 0) { creditScoreMapper.mapCreditScoreData(any()) }
    }


    @Test
    fun `load credit data use case returns ServerError and on retry return Success`() =
        runBlocking {
            val serverErrorResult = mockGetCreditScoreResultServerError
            val successResult = mockGetCreditScoreResultSuccess

            val initialState = CreditScoreHubState()

            // given we mock the repository
            coEvery { getCreditScoreUseCase.invoke() } returns serverErrorResult andThen successResult
            every { creditScoreMapper.mapCreditScoreData(any()) } returns mockCreditScoreHubData

            // when we observe details from the view model
            val viewModel = CreditScoreHubViewModel(getCreditScoreUseCase, creditScoreMapper).test(
                initialState = initialState,
            )
            viewModel.runOnCreate()

            viewModel.testIntent { onIntent(CreditScoreHubIntent.FetchCreditScore) }
            viewModel.testIntent { onIntent(CreditScoreHubIntent.FetchCreditScore) }

            // then the view model loads the overviews
            viewModel.assert(initialState) {
                states(
                    { copy(creditScoreLoadingState = CreditScoreLoadingState.Loading) },
                    {
                        copy(
                            creditScoreLoadingState = CreditScoreLoadingState.Error(
                                title = R.string.generic_error_title,
                                description = R.string.generic_error_subtitle
                            )
                        )
                    },
                    { copy(creditScoreLoadingState = CreditScoreLoadingState.Loading) },

                    {
                        copy(
                            creditScoreLoadingState = CreditScoreLoadingState.Success(
                                mockCreditScoreHubData
                            )
                        )
                    }
                )
            }

            // Verification
            coVerify(exactly = 2) { getCreditScoreUseCase.invoke() }
            coVerify(exactly = 1) { creditScoreMapper.mapCreditScoreData(any()) }

        }

}