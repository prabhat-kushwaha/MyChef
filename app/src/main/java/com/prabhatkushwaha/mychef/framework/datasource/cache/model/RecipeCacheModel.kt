package com.prabhatkushwaha.mychef.framework.datasource.cache.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@kotlinx.parcelize.Parcelize
@Entity
data class RecipeCacheModel(
    val image_url: String?,
    val social_rank: String?,
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val publisher: String?,
    val source_url: String?,
    val recipe_id:String,
    val publisher_url: String?,
    val title: String?,
):Parcelable