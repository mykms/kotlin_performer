package ru.dogwalk.performer.View

interface LoginView : BaseView {
    fun onLoginResult(login: String, token: String)
}