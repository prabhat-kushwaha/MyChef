package com.prabhatkushwaha.mychef.framework.presentation.ui.recipedetails.state

import android.os.Parcelable
import com.prabhatkushwaha.mychef.business.domain.model.Recipe
import com.prabhatkushwaha.mychef.business.domain.state.StateEvent
import kotlinx.parcelize.Parcelize


sealed class DetailsFragmentStateEvent {

    class GetRecipeDetailsEvent() : StateEvent {
        override fun errorInfo(): String {
            return "Recipe Details Not Found"
        }

        override fun stateEvent(): String {
            return "GetRecipeDetailsEvent"
        }

        override fun shouldShowProgressDialog(): Boolean {
            return true
        }

    }

    class SaveLikedRecipeEvent() : StateEvent {
        override fun errorInfo(): String {
            return "Recipe Not Saved Found"
        }

        override fun stateEvent(): String {
            return "SaveLikedRecipeEvent"
        }

        override fun shouldShowProgressDialog(): Boolean {
            return true
        }

    }

}
