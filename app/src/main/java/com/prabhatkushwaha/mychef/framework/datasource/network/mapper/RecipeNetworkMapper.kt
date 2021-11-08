package com.prabhatkushwaha.mychef.framework.datasource.network.mapper

import com.prabhatkushwaha.mychef.business.domain.model.Recipe
import com.prabhatkushwaha.mychef.business.domain.utils.EntityMapper
import com.prabhatkushwaha.mychef.framework.datasource.network.model.RecipeNetworkModel
import javax.inject.Singleton


@Singleton
class RecipeNetworkMapper : EntityMapper<RecipeNetworkModel, Recipe> {
    override fun fromEntity(entity: RecipeNetworkModel): Recipe {

        return Recipe(
            image_url = entity.image_url,
            social_rank = entity.social_rank,
            _id = entity._id,
            publisher = entity.publisher,
            source_url = entity.source_url,
            publisher_url = entity.publisher_url,
            title = entity.title,
            recipe_id = entity.recipe_id
        )


    }

    override fun toEntity(model: Recipe): RecipeNetworkModel {
        TODO("Not yet implemented")
    }


}
