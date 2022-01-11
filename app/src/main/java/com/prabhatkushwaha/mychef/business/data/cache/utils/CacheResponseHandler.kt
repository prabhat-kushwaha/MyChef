package com.prabhatkushwaha.mychef.business.data.cache.utils

import com.prabhatkushwaha.mychef.business.domain.state.*

abstract class CacheResponseHandler<RequestType, ResponseType>(
    private val cacheResponse: CacheResponse<ResponseType?>,
    private val stateEvent: StateEvent
) {

    suspend fun getResult(): DataState<RequestType?> {
        return when (cacheResponse) {
            is CacheResponse.GenericError -> DataState.error(
                Response(
                    "StateEvent:-> ${stateEvent.stateEvent()} Info${stateEvent.errorInfo()}",
                    UIComponentTypes.Toast(),
                    MessageType.Error()
                ), stateEvent = stateEvent
            )
            is CacheResponse.Success -> {
                if (cacheResponse.data == null) {
                    DataState.data(
                        Response(
                            CACHE_DATA_NULL_ERROR,
                            UIComponentTypes.Toast(),
                            MessageType.Info()
                        ),
                        stateEvent = stateEvent, data = null
                    )
                } else
                    handleResponse(cacheResponse.data)

            }
        }
    }

    abstract fun handleResponse(data: ResponseType): DataState<RequestType?>

}