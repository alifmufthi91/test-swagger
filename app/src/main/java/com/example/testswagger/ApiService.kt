package com.example.testswagger

import com.example.testswagger.login.LoginData
import com.example.testswagger.register.RegisterData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("register")
    fun register(
        @Body registerBody: RegisterData
    ): Call<ResponseBody>

    @POST("login")
    fun login(
        @Body loginBody: LoginData
    ): Call<ResponseBody>

    @GET("checklist")
    fun checklist(@Header("Authorization") auth: String): Call<ResponseChecklist>
}