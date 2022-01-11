package com.prabhatkushwaha.mychef.di


import androidx.lifecycle.ViewModelProvider
import androidx.paging.ExperimentalPagingApi
import com.prabhatkushwaha.mychef.business.interactors.home.HomeFragmentInteractors
import com.prabhatkushwaha.mychef.business.interactors.onboarding.OnBoardingFragmentInteractors
import com.prabhatkushwaha.mychef.business.interactors.recipedetails.DetailsFragmentInteractors
import com.prabhatkushwaha.mychef.business.interactors.siginin.SignInFragmentInteractors
import com.prabhatkushwaha.mychef.business.interactors.splash.SplashFragmentInteractors
import com.prabhatkushwaha.mychef.framework.presentation.ui.common.AppViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Singleton

@FlowPreview
@Module
@InstallIn(value = [SingletonComponent::class])
object AppViewModelModule {
    @ExperimentalCoroutinesApi
    @ExperimentalPagingApi
    @InternalCoroutinesApi
    @Singleton
    @Provides
    fun provideRecipeViewModelFactory(
        homeFragmentInteractors: HomeFragmentInteractors,
        detailsFragmentInteractors: DetailsFragmentInteractors,
        onBoardingFragmentInteractors: OnBoardingFragmentInteractors,
        splashFragmentInteractors: SplashFragmentInteractors,
        signInFragmentInteractors: SignInFragmentInteractors,
    ): ViewModelProvider.Factory {
        return AppViewModelFactory(
            homeFragmentInteractors,
            detailsFragmentInteractors,
            splashFragmentInteractors,
            onBoardingFragmentInteractors,
            signInFragmentInteractors
        )
    }
}