package com.prabhatkushwaha.mychef.framework.presentation.ui.common

import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import androidx.paging.ExperimentalPagingApi
import com.prabhatkushwaha.mychef.framework.presentation.ui.home.HomeFragment
import com.prabhatkushwaha.mychef.framework.presentation.ui.onboarding.OnBoardingFragment
import com.prabhatkushwaha.mychef.framework.presentation.ui.recipedetails.RecipeDetailsFragment
import com.prabhatkushwaha.mychef.framework.presentation.ui.signin.SignInFragment
import com.prabhatkushwaha.mychef.framework.presentation.ui.splash.SplashScreenFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
@ExperimentalPagingApi
@InternalCoroutinesApi
class AppFragmentFactory
@Inject
constructor(
    private val viewModelFactory: ViewModelProvider.Factory,
) : FragmentFactory() {


    override fun instantiate(classLoader: ClassLoader, className: String) =

        when (className) {

            HomeFragment::class.java.name -> {
                HomeFragment(viewModelFactory)
            }
            RecipeDetailsFragment::class.java.name -> {
                RecipeDetailsFragment(viewModelFactory)
            }
            SplashScreenFragment::class.java.name -> {
                SplashScreenFragment(viewModelFactory)
            }
            OnBoardingFragment::class.java.name -> {
                OnBoardingFragment(viewModelFactory)
            }
            SignInFragment::class.java.name -> {
                SignInFragment(viewModelFactory)
            }
            else -> {
                super.instantiate(classLoader, className)
            }
        }
}