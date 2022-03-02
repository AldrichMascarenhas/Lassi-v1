package com.example.common_data.networking.retrofit

import retrofit2.Response
import java.io.IOException

suspend fun <T, U> retrofitApiCall(
    call: suspend () -> Response<T>,
    success: (T) -> U,
    failure: (String?, Int) -> U,
    noInternet: () -> U
): U {

    return try {
        val response = call()
        val body = response.body()

        when {
            response.isSuccessful && body != null -> success(body)
            response.isSuccessful && body == null -> failure("No Body", response.code())
            else -> failure(response.errorBody()?.string(), response.code())
        }
    } catch (e: IOException) {
        noInternet()
    }
}