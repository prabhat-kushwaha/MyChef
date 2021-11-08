package com.prabhatkushwaha.mychef.di

import androidx.paging.ExperimentalPagingApi
import com.prabhatkushwaha.mychef.business.data.cache.abstraction.RecipeCacheDataSource
import com.prabhatkushwaha.mychef.business.data.network.abstarction.RecipeNetworkDataSource
import com.prabhatkushwaha.mychef.business.interactors.home.HomeFragmentInteractors
import com.prabhatkushwaha.mychef.business.interactors.home.SearchRecipe
import com.prabhatkushwaha.mychef.business.interactors.onboarding.OnBoardingFragmentInteractors
import com.prabhatkushwaha.mychef.business.interactors.recipedetails.DetailsFragmentInteractors
import com.prabhatkushwaha.mychef.business.interactors.recipedetails.GetRecipeDetails
import com.prabhatkushwaha.mychef.business.interactors.siginin.SignInFragmentInteractors
import com.prabhatkushwaha.mychef.business.interactors.splash.SplashFragmentInteractors
import com.prabhatkushwaha.mychef.framework.datasource.cache.mapper.RecipeCacheMapper
import com.prabhatkushwaha.mychef.framework.pref.RemoteKeyPrefManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(value = [SingletonComponent::class])
object InteractionModule {

    @ExperimentalPagingApi
    @Singleton
    @Provides
    fun providesHomeFragmentInteractor(
        recipeNetworkDataSource: RecipeNetworkDataSource,
        recipeCacheDataSource: RecipeCacheDataSource,
        remoteKeyPrefManager: RemoteKeyPrefManager,
        recipeCacheMapper: RecipeCacheMapper
    ): HomeFragmentInteractors {
        return HomeFragmentInteractors(
            SearchRecipe(
                recipeCacheMapper = recipeCacheMapper,
                remoteKeyPrefManager = remoteKeyPrefManager,
                recipeCacheDataSource = recipeCacheDataSource,
                recipeNetworkDataSource = recipeNetworkDataSource
            )
        )
    }


    @Singleton
    @Provides
    fun providesDetailsFragmentInteractor(
        recipeNetworkDataSource: RecipeNetworkDataSource,
    ): DetailsFragmentInteractors {
        return DetailsFragmentInteractors(
            GetRecipeDetails(
                recipeNetworkDataSource
            )
        )
    }


    @Singleton
    @Provides
    fun providesOnBoardingFragmentInteractor(
    ): OnBoardingFragmentInteractors {
        return OnBoardingFragmentInteractors(
        )
    }
    @Singleton
    @Provides
    fun providesSignInFragmentInteractor(
    ): SignInFragmentInteractors {
        return SignInFragmentInteractors(
        )
    }
    @Singleton
    @Provides
    fun providesSplashFragmentInteractor(
    ): SplashFragmentInteractors {
        return SplashFragmentInteractors()
    }

}