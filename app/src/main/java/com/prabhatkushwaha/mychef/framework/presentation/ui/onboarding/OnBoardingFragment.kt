package com.prabhatkushwaha.mychef.framework.presentation.ui.onboarding

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.prabhatkushwaha.mychef.R
import com.prabhatkushwaha.mychef.databinding.FragmentOnboardingBinding
import com.prabhatkushwaha.mychef.framework.presentation.ui.common.BaseFragment
import com.prabhatkushwaha.mychef.framework.presentation.ui.onboarding.state.OnBoardingStateEvent
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject


@InternalCoroutinesApi
class OnBoardingFragment @Inject constructor(factory: ViewModelProvider.Factory) :
    BaseFragment<FragmentOnboardingBinding>() {

    val viewModel: OnBoardingViewModel by viewModels {
        factory
    }

    var navController: NavController? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = Navigation.findNavController(view)
        super.onViewCreated(view, savedInstanceState)
    }


    override fun initialize() {

        binding.btGetStarted.setOnClickListener {
            viewModel.setStateEvent(OnBoardingStateEvent.MarkNewUserState())
            navController?.navigate(R.id.action_onBoardingFragment_to_signInFragment)
        }
    }

    override fun getBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentOnboardingBinding {
        return FragmentOnboardingBinding.inflate(inflater, container, false)
    }

    override fun subscribeObservers() {

        viewModel.viewState.observe(viewLifecycleOwner) {
            it?.let {

            }
        }
    }
}