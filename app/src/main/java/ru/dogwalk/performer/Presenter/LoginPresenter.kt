package ru.dogwalk.performer.Presenter

import ru.dogwalk.performer.Model.Sessions
import ru.dogwalk.performer.Network.ApiMethods
import ru.dogwalk.performer.View.LoginView

class LoginPresenter constructor(private val view: LoginView, private val apiMethods: ApiMethods) : BasePresenter() {
    fun login(login: String, password: String) {
        view.onProgress(true)
        val req = apiMethods.login(login, password)
        req.enqueue {
            onResponse = { response ->
                if (response.code() in 200..299) {
                    val sessions: Sessions? = response.body()
                    val res = sessions?.isSuccess() ?: false
                    if (res) {
                        view.onLoginResult(res, sessions?.phone!!, sessions.authentication_token)
                    } else {
                        view.onError("Не удалось войти, попробуйте позже!")
                    }
                } else {
                    view.onError("Запрос прошел, но есть ошибка ${response.code()}")
                }
                view.onProgress(false)
            }

            onFailure = { onFailure ->
                view.onError("Сетевая ошибка ${onFailure?.message}")
                view.onProgress(false)
            }
        }
    }
}