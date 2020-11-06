package com.example.testswagger

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class ApiMain {
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()
    private val builder = Retrofit.Builder()
        .baseUrl("http://18.139.50.74:8080")
        .client(okHttpClient)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
    private val retrofit = builder.build()
    val client: ApiService = retrofit.create(ApiService::class.java)
    var token = ""

    companion object {
        @Volatile
        private var instance: ApiMain? = null
        fun getInstance(): ApiMain =
            instance ?: synchronized(this) {
                instance ?: ApiMain().apply {
                    instance = this
                }
            }
    }
}