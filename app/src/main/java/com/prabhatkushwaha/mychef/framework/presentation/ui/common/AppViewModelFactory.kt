package com.prabhatkushwaha.mychef.framework.presentation.ui.common

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.ExperimentalPagingApi
import com.prabhatkushwaha.mychef.business.interactors.home.HomeFragmentInteractors
import com.prabhatkushwaha.mychef.business.interactors.onboarding.OnBoardingFragmentInteractors
import com.prabhatkushwaha.mychef.business.interactors.recipedetails.DetailsFragmentInteractors
import com.prabhatkushwaha.mychef.business.interactors.siginin.SignInFragmentInteractors
import com.prabhatkushwaha.mychef.business.interactors.splash.SplashFragmentInteractors
import com.prabhatkushwaha.mychef.framework.pref.LoginStatusPrefManager
import com.prabhatkushwaha.mychef.framework.presentation.ui.home.HomeViewModel
import com.prabhatkushwaha.mychef.framework.presentation.ui.onboarding.OnBoardingFragment
import com.prabhatkushwaha.mychef.framework.presentation.ui.onboarding.OnBoardingViewModel
import com.prabhatkushwaha.mychef.framework.presentation.ui.recipedetails.RecipeDetailsViewModel
import com.prabhatkushwaha.mychef.framework.presentation.ui.signin.SignInFragment
import com.prabhatkushwaha.mychef.framework.presentation.ui.signin.SignInViewModel
import com.prabhatkushwaha.mychef.framework.presentation.ui.signup.SignUpFragment
import com.prabhatkushwaha.mychef.framework.presentation.ui.splash.SplashScreenFragment
import com.prabhatkushwaha.mychef.framework.presentation.ui.splash.SplashScreenViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalCoroutinesApi
@FlowPreview
@ExperimentalPagingApi
@InternalCoroutinesApi
class AppViewModelFactory(
    private val homeFragmentInteractors: HomeFragmentInteractors,
    private val detailsFragmentInteractors: DetailsFragmentInteractors,
    private val splashFragmentInteractors: SplashFragmentInteractors,
    private val onBoardingFragmentInteractors: OnBoardingFragmentInteractors,
    private val signInFragmentInteractors: SignInFragmentInteractors,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return when (modelClass) {
            HomeViewModel::class.java -> {
                HomeViewModel(homeFragmentInteractors) as T
            }
            RecipeDetailsViewModel::class.java -> {
                RecipeDetailsViewModel(detailsFragmentInteractors) as T
            }
            SplashScreenViewModel::class.java -> {
                SplashScreenViewModel(splashFragmentInteractors) as T
            }
            OnBoardingViewModel::class.java -> {
                OnBoardingViewModel(onBoardingFragmentInteractors) as T
            }
            SignInViewModel::class.java -> {
                SignInViewModel(signInFragmentInteractors) as T
            }

            else -> {
                throw IllegalArgumentException("unknown model class $modelClass")
            }
        }
    }
}