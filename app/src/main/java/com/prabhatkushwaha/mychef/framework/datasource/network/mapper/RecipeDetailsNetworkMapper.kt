package com.prabhatkushwaha.mychef.framework.datasource.network.mapper

import com.prabhatkushwaha.mychef.business.domain.extensions.getStringValue
import com.prabhatkushwaha.mychef.business.domain.model.RecipeDetailsModel
import com.prabhatkushwaha.mychef.business.domain.utils.EntityMapper
import com.prabhatkushwaha.mychef.framework.datasource.network.model.RecipeDetailsNetworkModel
import javax.inject.Singleton


@Singleton
class RecipeDetailsNetworkMapper : EntityMapper<RecipeDetailsNetworkModel, RecipeDetailsModel> {
    override fun fromEntity(entity: RecipeDetailsNetworkModel): RecipeDetailsModel {
        return RecipeDetailsModel(
            _id = entity.recipe._id,
            image_url = entity.recipe.image_url,
            ingredients = entity.recipe.ingredients.getStringValue(),
            publisher = entity.recipe.publisher,
            publisher_url = entity.recipe.publisher_url,
            recipe_id = entity.recipe.recipe_id,
            social_rank = entity.recipe.social_rank,
            source_url = entity.recipe.source_url,
            title = entity.recipe.title, toolbarColor = null
        )
    }

    override fun toEntity(model: RecipeDetailsModel): RecipeDetailsNetworkModel {
        TODO("Not yet implemented")
    }


}
