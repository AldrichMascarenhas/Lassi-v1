package com.example.credit_score.di

import com.example.credit_score.ui.credit_score_hub.CreditScoreHubViewModel
import com.example.credit_score.usecase.GetCreditScoreUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val credit_score_module = module {

    // Use Case
    factory { GetCreditScoreUseCase(creditScoreRepository = get()) }

    // ViewModel
    viewModel { CreditScoreHubViewModel(getCreditScoreUseCase = get()) }

}