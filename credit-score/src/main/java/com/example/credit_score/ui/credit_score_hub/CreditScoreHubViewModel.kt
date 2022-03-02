package com.example.credit_score.ui.credit_score_hub

import androidx.lifecycle.ViewModel
import com.example.strings.R as RS
import com.example.credit_score.usecase.GetCreditScoreResult
import com.example.credit_score.usecase.GetCreditScoreUseCase
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class CreditScoreHubViewModel(
    private val getCreditScoreUseCase: GetCreditScoreUseCase
) : ContainerHost<CreditScoreHubState, Nothing>, ViewModel() {

    override val container =
        container<CreditScoreHubState, Nothing>(CreditScoreHubState())

    fun onIntent(creditScoreHubIntent: CreditScoreHubIntent) {
        when (creditScoreHubIntent) {
            CreditScoreHubIntent.FetchCreditScore -> fetchCreditScore()
        }
    }

    private fun fetchCreditScore() = intent {
        delay(1000) // False delay to show loading
        reduce { state.copy(creditScoreLoadingState = CreditScoreLoadingState.Loading) }
        when (val response = getCreditScoreUseCase.invoke()) {
            GetCreditScoreResult.NoInternet -> reduce {
                state.copy(creditScoreLoadingState = CreditScoreLoadingState.Error(
                    title = RS.string.no_internet_body, description = RS.string.no_internet_body
                ))
            }
            GetCreditScoreResult.ServerError -> reduce {
                state.copy(creditScoreLoadingState = CreditScoreLoadingState.Error(
                    title = RS.string.generic_error_title, description = RS.string.generic_error_subtitle
                ))
            }
            is GetCreditScoreResult.Success -> reduce {
                state.copy(
                    creditScoreLoadingState = CreditScoreLoadingState.Success(
                        response.creditScoreData
                    )
                )
            }
        }
    }
}