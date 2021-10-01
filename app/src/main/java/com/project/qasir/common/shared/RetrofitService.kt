package com.project.qasir.common.shared

import android.content.Context
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitService {

    const val BASE_URL = "https://api.jsonbin.io/"

    inline fun <reified I> callAPI(context: Context) : I {
        return builder(BASE_URL)
    }

    inline fun <reified I> builder(api: String) : I {
        val retrofit = Retrofit.Builder()
            .baseUrl(api)
            .client(getOKHttp())
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        return retrofit.create(I::class.java)
    }

    fun getOKHttp() : OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(getInterceptor())
        .addInterceptor(getHTTPLoggingInterceptor())
        .connectTimeout(59, TimeUnit.SECONDS)
        .writeTimeout(59, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .build()

    private fun getHTTPLoggingInterceptor() : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        HttpLoggingInterceptor.Level.BODY
    }

    private fun getInterceptor(): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            val request: Request = chain.request().newBuilder()
                .cacheControl(CacheControl.Builder().noCache().build())
                .build()
            chain.proceed(request)
        }
    }

}