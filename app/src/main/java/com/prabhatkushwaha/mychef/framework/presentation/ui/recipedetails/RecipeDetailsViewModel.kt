package com.prabhatkushwaha.mychef.framework.presentation.ui.recipedetails

import android.util.Log
import com.prabhatkushwaha.mychef.business.domain.model.RecipeDetailsModel
import com.prabhatkushwaha.mychef.business.domain.state.DataState
import com.prabhatkushwaha.mychef.business.domain.state.StateEvent
import com.prabhatkushwaha.mychef.business.interactors.recipedetails.DetailsFragmentInteractors
import com.prabhatkushwaha.mychef.framework.presentation.ui.common.BaseViewModel
import com.prabhatkushwaha.mychef.framework.presentation.ui.recipedetails.state.DetailsFragmentStateEvent
import com.prabhatkushwaha.mychef.framework.presentation.ui.recipedetails.state.DetailsFragmentViewState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecipeDetailsViewModel @Inject constructor(private val interactor: DetailsFragmentInteractors) :
    BaseViewModel<DetailsFragmentViewState>() {


    override fun setStateEvent(stateEvent: StateEvent) {

        val job: Flow<DataState<DetailsFragmentViewState>?> = when (stateEvent) {
            is DetailsFragmentStateEvent.GetRecipeDetailsEvent -> interactor.getRecipeDetails.getRecipeDetails(
                getRecipeDetailsId(), stateEvent
            )

            else -> {
                emitInvalidStateEvent(stateEvent)
            }
        }
        launchJob(stateEvent, job)
    }

    override fun handleNewData(data: DetailsFragmentViewState) {
        data.let { viewState ->
            viewState.recipe?.let {
                setRecipe(it)
            }
        }
    }

    private fun setRecipe(recipe: RecipeDetailsModel) {
        val update = getCurrentViewStateOrNew()
        update.recipe = recipe
        setViewState(update)
    }

    fun setRecipeDetailsId(id: String) {
        val update = getCurrentViewStateOrNew()
        update.recipeDetailsId = id
        setViewState(update)
    }

    private fun getRecipeDetailsId(): String {
        return getCurrentViewStateOrNew().recipeDetailsId ?: ""
    }

    private fun getRecipe(): RecipeDetailsModel? {
        return getCurrentViewStateOrNew().recipe
    }

    override fun initNewViewState(): DetailsFragmentViewState {
        return DetailsFragmentViewState()
    }
}