package com.movie.app.shared.network.interceptor

import com.movie.app.shared.util.SharedPref
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class TokenInterceptor(val pref: SharedPref) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        request = request.newBuilder()
                .addHeader("Authorization", "Bearer ${pref.token}")
                .build()

        println("accessToken = ${pref.token}")

        return chain.proceed(request)
    }
}
