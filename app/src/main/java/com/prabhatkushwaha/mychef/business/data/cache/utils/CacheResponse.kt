package com.prabhatkushwaha.mychef.business.data.cache.utils

sealed class CacheResponse<out T> {
    data class Success<out T>(val data: T?) : CacheResponse<T>()
    data class GenericError(val errorMessage: String? = null) : CacheResponse<Nothing>()
}
