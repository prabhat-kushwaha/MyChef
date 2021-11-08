package com.prabhatkushwaha.mychef.business.interactors.recipedetails

import com.prabhatkushwaha.mychef.business.data.network.abstarction.RecipeNetworkDataSource
import com.prabhatkushwaha.mychef.business.data.network.utils.ApiResponseHandler
import com.prabhatkushwaha.mychef.business.domain.model.RecipeDetailsModel
import com.prabhatkushwaha.mychef.business.domain.state.DataState
import com.prabhatkushwaha.mychef.framework.datasource.utils.safeApiCall
import com.prabhatkushwaha.mychef.framework.presentation.ui.recipedetails.state.DetailsFragmentStateEvent
import com.prabhatkushwaha.mychef.framework.presentation.ui.recipedetails.state.DetailsFragmentViewState
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRecipeDetails @Inject constructor(private val recipeNetworkDataSource: RecipeNetworkDataSource) {


    fun getRecipeDetails(
        id: String,
        stateEvent: DetailsFragmentStateEvent.GetRecipeDetailsEvent
    ): Flow<DataState<DetailsFragmentViewState>?> = flow {

        val networkResult = safeApiCall(IO) {
            recipeNetworkDataSource.getRecipeById(id)
        }

        val response = object : ApiResponseHandler<DetailsFragmentViewState, RecipeDetailsModel>(
            networkResult,
            stateEvent
        ) {
            override suspend fun handleSuccess(resultObj: RecipeDetailsModel): DataState<DetailsFragmentViewState>? {
                val viewState = DetailsFragmentViewState(recipe = resultObj)
                return DataState(null, data = viewState, stateEvent = stateEvent)
            }
        }.getResult()
        emit(response)
    }

}