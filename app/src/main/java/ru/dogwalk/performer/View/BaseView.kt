package ru.dogwalk.performer.View

interface BaseView {
    fun onError(message: String)
    fun onProgress(isProgress: Boolean)
}