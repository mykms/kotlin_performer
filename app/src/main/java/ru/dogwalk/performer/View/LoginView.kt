package ru.dogwalk.performer.View

interface LoginView : BaseView {
    fun onLoginResult(isSuccess: Boolean, login: String, token: String)
}