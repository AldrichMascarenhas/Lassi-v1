package com.example.credit_score.ui.credit_score_hub

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.credit_score.R
import com.example.strings.R as RS
import com.example.credit_score.databinding.FragmentCreditScoreHubBinding
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import org.koin.androidx.viewmodel.ext.android.stateViewModel
import org.orbitmvi.orbit.viewmodel.observe

class CreditScoreHubFragment : Fragment(R.layout.fragment_credit_score_hub) {

    // Inject as a stateViewModel in your Activity or Fragment
    private val viewModel by stateViewModel<CreditScoreHubViewModel>()

    private val binding by viewBinding(FragmentCreditScoreHubBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.observe(viewLifecycleOwner, state = ::render, sideEffect = {})

        viewModel.onIntent(CreditScoreHubIntent.FetchCreditScore)
    }

    private fun render(state: CreditScoreHubState) {
        when (state.creditScoreLoadingState) {

            is CreditScoreLoadingState.Error -> handleErrorState(
                state.creditScoreLoadingState.title,
                state.creditScoreLoadingState.description
            )
            CreditScoreLoadingState.Loading -> {
                binding.apply {
                    creditScoreIndicator.isVisible = false
                    progressBar.isVisible = true
                    errorContainer.isVisible = false
                }
            }
            is CreditScoreLoadingState.Success -> {

                binding.apply {
                    creditScoreIndicator.isVisible = true
                    progressBar.isVisible = false
                    errorContainer.isVisible = false

                    creditScoreIndicator.apply {
                        state.creditScoreLoadingState.creditScoreHubData.let {
                            indicator.max = it.maxScoreValue
                            indicator.setProgress(it.score, true)
                            title.text = state.creditScoreLoadingState.creditScoreHubData.title
                            subtitle.text = it.subtitle
                            value.text = it.value
                        }
                    }
                }
            }
        }
    }

    private fun handleErrorState(@StringRes title: Int, @StringRes description: Int) {

        binding.apply {
            creditScoreIndicator.isVisible = false
            progressBar.isVisible = false
            errorContainer.isVisible = true
        }

        binding.errorCollectionView.title.text = getString(title)
        binding.errorCollectionView.subtitle.text = getString(description)
        binding.errorRetryButton.setOnClickListener {
            viewModel.onIntent(CreditScoreHubIntent.FetchCreditScore)
        }
    }
}