package com.prabhatkushwaha.mychef.framework.datasource.cache.database

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.prabhatkushwaha.mychef.framework.datasource.cache.model.RecipeCacheModel


@Dao
interface RecipeDao {

    @Insert
    suspend fun insertRecipes(list: List<RecipeCacheModel>)

    @Query("SELECT * FROM RecipeCacheModel")
    fun getAllRecipe(): LiveData<List<RecipeCacheModel>>


    @Query("DELETE FROM RecipeCacheModel")
    suspend fun deleteAllRecipe(): Int


    @Query("SELECT * FROM RecipeCacheModel")
    fun getRecipePagingSource(): PagingSource<Int, RecipeCacheModel>




}