package com.prabhatkushwaha.mychef.framework.datasource.cache.mapper

import com.prabhatkushwaha.mychef.business.domain.model.Recipe
import com.prabhatkushwaha.mychef.business.domain.model.RecipeLikedModel
import com.prabhatkushwaha.mychef.business.domain.utils.EntityMapper
import com.prabhatkushwaha.mychef.framework.datasource.cache.model.RecipeCacheModel
import com.prabhatkushwaha.mychef.framework.datasource.cache.model.RecipeLikedCacheModel
import javax.inject.Singleton


@Singleton
class RecipeLikeCacheMapper : EntityMapper<RecipeLikedModel, RecipeLikedCacheModel> {
    override fun fromEntity(entity: RecipeLikedModel): RecipeLikedCacheModel {
        return RecipeLikedCacheModel(title = entity.recipeName, id = null)
    }

    override fun toEntity(model: RecipeLikedCacheModel): RecipeLikedModel {
        return RecipeLikedModel(model.title)
    }


}
