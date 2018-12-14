package ru.dogwalk.performer.Presenter

import ru.dogwalk.performer.Model.Client
import ru.dogwalk.performer.Network.ApiMethods
import ru.dogwalk.performer.View.UserProfileView

class UserProfilePresenter(private val view: UserProfileView, private val apiMethods: ApiMethods) : BasePresenter() {
    fun getUserInfo() {
        view.onProgress(true)
        val req = apiMethods.getUserInfo()
        req.enqueue {
            onResponse = { response ->
                if (response.code() in 200..299) {
                    val client: Client? = response.body()
                    view.onUserInfo(client)
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