package com.xavidev.pokeapp.utils

sealed class Response<out T>(
    val data: T? = null,
    val status: Status? = null,
    val message: String? = null
) {
    class Loading<T>(status: Status = Status.LOADING) : Response<T>(null, status)

    class Success<T>(data: T, status: Status = Status.SUCCESS) : Response<T>(data, status)

    class Error<T>(message: String?, status: Status = Status.ERROR) :
        Response<T>(null, status, message)
}