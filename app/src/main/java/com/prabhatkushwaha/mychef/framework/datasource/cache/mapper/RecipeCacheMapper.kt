package com.prabhatkushwaha.mychef.framework.datasource.cache.mapper

import com.prabhatkushwaha.mychef.business.domain.model.Recipe
import com.prabhatkushwaha.mychef.business.domain.utils.EntityMapper
import com.prabhatkushwaha.mychef.framework.datasource.cache.model.RecipeCacheModel
import javax.inject.Singleton


@Singleton
class RecipeCacheMapper : EntityMapper<RecipeCacheModel, Recipe> {
    override fun fromEntity(entity: RecipeCacheModel): Recipe {
        return  Recipe(
            image_url = entity.image_url,
            social_rank = entity.social_rank,
            _id = entity.id,
            publisher = entity.publisher,
            source_url = entity.source_url,
            publisher_url = entity.publisher_url,
            title = entity.title,
            recipe_id = entity.recipe_id
        )
    }

    override fun toEntity(model: Recipe): RecipeCacheModel {
        return RecipeCacheModel(
            image_url = model.image_url,
            social_rank = model.social_rank,
            id = model._id,
            publisher = model.publisher,
            source_url = model.source_url,
            publisher_url = model.publisher_url,
            title = model.title,
            recipe_id = model.recipe_id
        )



    }


}
