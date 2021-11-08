package com.prabhatkushwaha.mychef.framework.datasource.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecipeDetailsCacheModel(
    val cooking_instructions: String?,
    val date_added: String?,
    val date_updated: String?,
    val description: String?,
    val featured_image: String?,
    val ingredients: String,
    val long_date_added: Int?,
    val long_date_updated: Int?,
    @PrimaryKey(autoGenerate = false)
    val pk: String,
    val publisher: String?,
    val rating: Int?,
    val source_url: String?,
    val title: String?
)