package com.example.testswagger

import com.google.gson.annotations.SerializedName

data class ResponseBody(
    @SerializedName("statusCode")
    var statusCode: Int?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("data")
    var data: Data?
) {

    data class Data(
        @SerializedName("token")
        val token: String
    )
}