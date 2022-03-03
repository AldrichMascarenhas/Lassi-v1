package com.example.credit_score_data.datasource.util

import okhttp3.mockwebserver.MockResponse
import java.io.File

/**
 * Move this to a common:test package and use testImplementation in data modules
 */
object MockWebServerUtils {

    fun getMockedHttpResponse(
        fileName: String? = null,
        responseCode: Int? = null
    ): MockResponse {
        val mockResponse = MockResponse()
        responseCode?.let {
            mockResponse.setResponseCode(it)
        }
        fileName?.let {
            mockResponse.setBody(getJson("$fileName"))
        }
        return mockResponse
    }

    private fun getJson(path: String): String {
        val uri = javaClass.classLoader!!.getResource(path)
        val file = File(uri.path)
        return String(file.readBytes())
    }
}