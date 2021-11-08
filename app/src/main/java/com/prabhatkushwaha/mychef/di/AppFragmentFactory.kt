package com.prabhatkushwaha.mychef.di

import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import androidx.paging.ExperimentalPagingApi
import com.prabhatkushwaha.mychef.framework.presentation.ui.common.AppFragmentFactory
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Singleton

@Module
@InstallIn(value = [SingletonComponent::class])
object AppFragmentFactory {

    @ExperimentalPagingApi
    @InternalCoroutinesApi
    @FlowPreview
    @ExperimentalCoroutinesApi
    @Singleton
    @Provides
    fun provideNoteFragmentFactory(
        viewModelFactory: ViewModelProvider.Factory,
        ): FragmentFactory {
        return AppFragmentFactory(
            viewModelFactory
            )
    }

  /*  interface Factory{
        fun create(@BindsInstance app: MyChef): com.prabhatkushwaha.mychef.di.AppFragmentFactory
    }*/


}