package com.prabhatkushwaha.mychef.framework.datasource.cache.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@kotlinx.parcelize.Parcelize
@Entity
data class RecipeLikedCacheModel(
    @PrimaryKey(autoGenerate = true)
    val id:Int?,
    val title: String
):Parcelable