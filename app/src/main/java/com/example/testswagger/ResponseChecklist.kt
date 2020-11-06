package com.example.testswagger

import com.google.gson.annotations.SerializedName

data class ResponseChecklist(
    @SerializedName("message")
    var message: String?,
    @SerializedName("data")
    var data: List<ChecklistItem>?
)