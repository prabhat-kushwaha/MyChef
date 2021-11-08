package com.prabhatkushwaha.mychef.framework.datasource.cache.database

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.prabhatkushwaha.mychef.framework.datasource.cache.model.RecipeCacheModel

@Database(entities = [ RecipeCacheModel::class], version = 1,exportSchema = false)
abstract class RecipeDataBase : RoomDatabase() {

    abstract fun getDao(): RecipeDao

    companion object {
       val DB_NAME="RECIPE_DB"
    }

    override fun createOpenHelper(config: DatabaseConfiguration?): SupportSQLiteOpenHelper {
        TODO("Not yet implemented")
    }

    override fun createInvalidationTracker(): InvalidationTracker {
        TODO("Not yet implemented")
    }

    override fun clearAllTables() {
        TODO("Not yet implemented")
    }
}