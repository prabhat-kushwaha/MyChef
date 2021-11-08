package com.prabhatkushwaha.mychef.framework.datasource.utils

import android.util.Log
import com.prabhatkushwaha.mychef.business.data.cache.utils.CACHE_TIMEOUT
import com.prabhatkushwaha.mychef.business.data.cache.utils.CACHE_TIMEOUT_ERROR
import com.prabhatkushwaha.mychef.business.data.cache.utils.CACHE_UNKNOWN_ERROR
import com.prabhatkushwaha.mychef.business.data.cache.utils.CacheResponse
import com.prabhatkushwaha.mychef.business.data.network.utils.ApiResult
import com.prabhatkushwaha.mychef.business.data.network.utils.NETWORK_TIMEOUT
import com.prabhatkushwaha.mychef.business.data.network.utils.NETWORK_TIMEOUT_ERROR
import com.prabhatkushwaha.mychef.business.data.network.utils.NETWORK_UNKNOWN_ERROR
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.io.IOException

/**
 * Reference: https://medium.com/@douglas.iacovelli/how-to-handle-errors-with-retrofit-and-coroutines-33e7492a912
 */

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T?
): ApiResult<T?> {
    return withContext(dispatcher) {
        try {
            // throws TimeoutCancellationException
            withTimeout(NETWORK_TIMEOUT){
                ApiResult.Success(apiCall.invoke())
            }
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            when (throwable) {
                is TimeoutCancellationException -> {
                    val code = 408 // timeout error code
                    ApiResult.GenericError(code, NETWORK_TIMEOUT_ERROR)
                }
                is IOException -> {
                    ApiResult.NetworkError
                }
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse = convertErrorBody(throwable)

                    ApiResult.GenericError(
                        code,
                        errorResponse
                    )
                }
                else -> {
                    ApiResult.GenericError(
                        null,
                        NETWORK_UNKNOWN_ERROR
                    )
                }
            }
        }
    }
}

suspend fun <T> safeCacheCall(
    dispatcher: CoroutineDispatcher,
    cacheCall: suspend () -> T?
): CacheResponse<T?> {
    return withContext(dispatcher) {
        try {
            // throws TimeoutCancellationException
            withTimeout(CACHE_TIMEOUT){
                CacheResponse.Success(cacheCall.invoke())
            }
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            when (throwable) {

                is TimeoutCancellationException -> {
                    CacheResponse.GenericError(CACHE_TIMEOUT_ERROR)
                }
                else -> {
                    CacheResponse.GenericError(CACHE_UNKNOWN_ERROR)
                }
            }
        }
    }
}


private fun convertErrorBody(throwable: HttpException): String? {
    return try {
        throwable.response()?.errorBody()?.string()
    } catch (exception: Exception) {
        NETWORK_UNKNOWN_ERROR
    }
}























