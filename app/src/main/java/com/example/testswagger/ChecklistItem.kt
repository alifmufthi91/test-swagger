package com.example.testswagger

import com.google.gson.annotations.SerializedName

data class ChecklistItem(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("checklistCompletionStatus")
    val status: Boolean?
)