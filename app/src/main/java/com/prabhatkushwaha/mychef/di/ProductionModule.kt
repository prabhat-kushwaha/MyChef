package com.prabhatkushwaha.mychef.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.room.Room
import com.prabhatkushwaha.mychef.business.data.cache.abstraction.RecipeCacheDataSource
import com.prabhatkushwaha.mychef.business.data.cache.impl.RecipeCacheDataSourceImpl
import com.prabhatkushwaha.mychef.business.data.network.abstarction.RecipeNetworkDataSource
import com.prabhatkushwaha.mychef.business.data.network.impl.RecipeNetworkDataSourceImpl
import com.prabhatkushwaha.mychef.framework.datasource.cache.abstarction.RecipeDaoService
import com.prabhatkushwaha.mychef.framework.datasource.cache.database.RecipeDao
import com.prabhatkushwaha.mychef.framework.datasource.cache.database.RecipeDataBase
import com.prabhatkushwaha.mychef.framework.datasource.cache.impl.RecipeDaoServiceImpl
import com.prabhatkushwaha.mychef.framework.datasource.cache.mapper.RecipeCacheMapper
import com.prabhatkushwaha.mychef.framework.datasource.network.abstaction.RecipeApiService
import com.prabhatkushwaha.mychef.framework.datasource.network.impl.RecipeApiServiceImpl
import com.prabhatkushwaha.mychef.framework.datasource.network.mapper.RecipeDetailsNetworkMapper
import com.prabhatkushwaha.mychef.framework.datasource.network.mapper.RecipeNetworkMapper
import com.prabhatkushwaha.mychef.framework.datasource.network.service.RecipeService
import com.prabhatkushwaha.mychef.framework.pref.LoginStatusPrefManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(value = [SingletonComponent::class])
object ProductionModule {
    @Singleton
    @Provides
    fun provideRecipeDb(@ApplicationContext context: Context): RecipeDataBase {
        return Room.databaseBuilder(context, RecipeDataBase::class.java, RecipeDataBase.DB_NAME)
            .fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideRecipeDao(recipeDataBase: RecipeDataBase): RecipeDao {
        return recipeDataBase.getDao()
    }

    @Singleton
    @Provides
    fun provideLoginStatusPref(@ApplicationContext context: Context): LoginStatusPrefManager {
        return LoginStatusPrefManager(context)
    }



    @Singleton
    @Provides
    fun providesNetworkDataSource(recipeApiService: RecipeApiService): RecipeNetworkDataSource {
        return RecipeNetworkDataSourceImpl(recipeApiService)
    }

    @ExperimentalPagingApi
    @Singleton
    @Provides
    fun providesCacheDataSource(recipeDaoService: RecipeDaoService): RecipeCacheDataSource {
        return RecipeCacheDataSourceImpl(recipeDaoService)
    }

    @ExperimentalPagingApi
    @Singleton
    @Provides
    fun providesRecipeDaoService(
        recipeDao: RecipeDao,
        recipeCacheMapper: RecipeCacheMapper
    ): RecipeDaoService {
        return RecipeDaoServiceImpl(recipeDao, recipeCacheMapper)
    }

    @Singleton
    @Provides
    fun providesRecipeMapper(): RecipeNetworkMapper {
        return RecipeNetworkMapper()
    }


    @Singleton
    @Provides
    fun providesRecipeCacheMapper(): RecipeCacheMapper {
        return RecipeCacheMapper()
    }

    @Singleton
    @Provides
    fun providesRecipeDetailsNetworkMapper(): RecipeDetailsNetworkMapper {
        return RecipeDetailsNetworkMapper()
    }


    @Singleton
    @Provides
    fun providesNetworkApiService(
        recipeService: RecipeService,
        recipeNetworkMapper: RecipeNetworkMapper,
        recipeDetailsNetworkMapper: RecipeDetailsNetworkMapper
    ): RecipeApiService {
        return RecipeApiServiceImpl(recipeService, recipeNetworkMapper, recipeDetailsNetworkMapper)
    }
}