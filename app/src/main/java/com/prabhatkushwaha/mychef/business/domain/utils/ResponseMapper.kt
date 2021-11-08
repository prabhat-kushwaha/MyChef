package com.prabhatkushwaha.mychef.business.domain.utils

interface ResponseMapper<NetworkResponse, CustomResponse> {
    fun fromNetwork(networkResponse: NetworkResponse): CustomResponse
    fun toNetwork(customResponse: CustomResponse): NetworkResponse
}