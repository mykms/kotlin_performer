package ru.dogwalk.performer.Model

open class BaseModel {
    val status: String = "error"

    fun isSuccess(): Boolean {
        return status == "success"
    }
}