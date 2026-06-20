package com.rainyday.foodrun.core.network.interceptors

import com.rainyday.foodrun.core.datastore.TokenDataStore
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenDataStore: TokenDataStore
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        // Retrieve token
        val token = runBlocking {
            tokenDataStore.token.firstOrNull()
        }

        val request = if (token.isNullOrEmpty()) {
            chain.request()
        } else {
            chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        }

        return chain.proceed(request)
    }
}