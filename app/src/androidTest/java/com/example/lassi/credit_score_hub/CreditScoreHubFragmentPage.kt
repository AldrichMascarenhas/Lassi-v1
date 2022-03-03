package com.example.lassi.credit_score_hub

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.credit_score.R
import com.example.credit_score.mapper.CreditScoreMapper
import com.example.credit_score.ui.credit_score_hub.CreditScoreHubIntent
import com.example.credit_score.ui.credit_score_hub.CreditScoreHubViewModel
import com.example.credit_score.usecase.GetCreditScoreResult
import com.example.credit_score.usecase.GetCreditScoreUseCase
import com.example.lassi.MainActivity
import com.example.lassi.credit_score_hub.data.*
import io.mockk.coEvery
import io.mockk.mockk
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.containsString
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext.loadKoinModules
import org.koin.core.context.GlobalContext.unloadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import java.lang.Thread.sleep

@RunWith(AndroidJUnit4::class)
@LargeTest
class CreditScoreHubFragmentPage {

    @Rule
    @JvmField
    val rule = ActivityTestRule(MainActivity::class.java, true, false)

    private val creditScoreIndicator = withId(R.id.creditScoreIndicator)
    private val progressBar = withId(R.id.progressBar)
    private val errorContainer = withId(R.id.errorContainer)
    private val errorRetryButton = withId(R.id.errorRetryButton)

    lateinit var fragmentViewModel: CreditScoreHubViewModel
    private lateinit var module: Module

    private val getCreditScoreUseCase = mockk<GetCreditScoreUseCase>(relaxed = true)
    private val creditScoreMapper = mockk<CreditScoreMapper>(relaxed = true)

    @Before
    fun setUp() {
        fragmentViewModel = CreditScoreHubViewModel(getCreditScoreUseCase, creditScoreMapper)

        module = module {
            single { getCreditScoreUseCase }
            single { creditScoreMapper }
            single { fragmentViewModel }

        }
        loadKoinModules(module)
        rule.launchActivity(null)
    }

    @After
    fun tearDown() {
        unloadKoinModules(module)
    }

    @Test
    fun successCase() {

        // Given
        coEvery { getCreditScoreUseCase.invoke() } returns GetCreditScoreResult.Success(
            mockCreditScoreData
        )
        coEvery { creditScoreMapper.mapCreditScoreData(any()) } returns mockCreditScoreHubData

        onView(progressBar).check(matches(isDisplayed()));

        // When
        fragmentViewModel.onIntent(CreditScoreHubIntent.FetchCreditScore)

        // Then
        sleep(2000)
        onView(creditScoreIndicator).check(matches(isDisplayed()));
        onView(
            allOf(
                withId(com.example.ui_components.R.id.textviewValue),
                isDescendantOfA(withId(R.id.creditScoreIndicator))
            )
        ).check(matches(isDisplayed()))
            .check(matches(withText(containsString(CREDIT_SCORE_HUB_INDICATOR_VALUE))));

        onView(
            allOf(
                withId(com.example.ui_components.R.id.textviewTitle),
                isDescendantOfA(withId(R.id.creditScoreIndicator))
            )
        ).check(matches(isDisplayed()))
            .check(matches(withText(containsString(CREDIT_SCORE_HUB_INDICATOR_TITLE))));

        onView(
            allOf(
                withId(com.example.ui_components.R.id.textviewSubTitle),
                isDescendantOfA(withId(R.id.creditScoreIndicator))
            )
        ).check(matches(isDisplayed()))
            .check(matches(withText(containsString(CREDIT_SCORE_HUB_INDICATOR_SUBTITLE))));
    }

    @Test
    fun errorCase() {
        // Given
        coEvery { getCreditScoreUseCase.invoke() } returns GetCreditScoreResult.NoInternet
        onView(progressBar).check(matches(isDisplayed()));

        // When
        fragmentViewModel.onIntent(CreditScoreHubIntent.FetchCreditScore)

        // Then
        sleep(2000)
        onView(errorContainer).check(matches(isDisplayed()))

        // Given
        coEvery { getCreditScoreUseCase.invoke() } returns GetCreditScoreResult.Success(
            mockCreditScoreData
        )
        coEvery { creditScoreMapper.mapCreditScoreData(any()) } returns mockCreditScoreHubData

        // When
        onView(errorRetryButton).perform(ViewActions.click())

        // Then
        sleep(2000)
        onView(creditScoreIndicator).check(matches(isDisplayed()));
    }

}