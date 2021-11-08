package com.prabhatkushwaha.mychef.framework.presentation.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.prabhatkushwaha.mychef.business.domain.model.Recipe
import com.prabhatkushwaha.mychef.business.domain.state.DataState
import com.prabhatkushwaha.mychef.business.domain.state.StateEvent
import com.prabhatkushwaha.mychef.business.interactors.home.HomeFragmentInteractors
import com.prabhatkushwaha.mychef.framework.presentation.ui.common.BaseViewModel
import com.prabhatkushwaha.mychef.framework.presentation.ui.home.state.HomeFragmentViewState
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

const val PAGE_SIZE = 25

@Singleton
@InternalCoroutinesApi
@ExperimentalPagingApi
class HomeViewModel @Inject
constructor(
    private val interactors: HomeFragmentInteractors
) : BaseViewModel<HomeFragmentViewState>() {


    val recipe: MutableLiveData<PagingData<Recipe>> by lazy {
        MutableLiveData<PagingData<Recipe>>()
    }


    fun getRecipe() {
        Log.d(TAG, "getRecipe: ${getSearchQuery()}")
        viewModelScope.launch {
            interactors.searchRecipes.getRecipeList(
                search = getSearchQuery(),
                pagingConfig = PagingConfig(
                    PAGE_SIZE,
                    enablePlaceholders = false
                )
            ).cachedIn(viewModelScope).collect {
                recipe.value = it
            }
        }

    }

    override fun handleNewData(viewState: HomeFragmentViewState) {
        viewState.let {

        }
    }


    override fun initNewViewState(): HomeFragmentViewState {
        return HomeFragmentViewState()
    }

    private fun getSearchQuery(): String {
        return getCurrentViewStateOrNew().searchQuery
            ?: return "chicken"
    }

    override fun setStateEvent(stateEvent: StateEvent) {
        val job: Flow<DataState<HomeFragmentViewState>?> = when (stateEvent) {

            else -> {
                emitInvalidStateEvent(stateEvent)
            }
        }
        launchJob(stateEvent, job)
    }

    fun setQuery(query: String?) {
        val update = getCurrentViewStateOrNew()
        update.searchQuery = query
        setViewState(update)
    }

}