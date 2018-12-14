package ru.dogwalk.performer.Presenter

import ru.dogwalk.performer.Model.Order
import ru.dogwalk.performer.Network.ApiMethods
import ru.dogwalk.performer.View.OrderView

class OrderPresenter(private val view: OrderView, private val apiMethods: ApiMethods) : BasePresenter() {
    fun getOrderList(isHot: Boolean = false) {
        view.onProgress(true)
        val req = apiMethods.getOrders(isHot)
        req.enqueue {
            onResponse = { response ->
                if (response.code() in 200..299) {
                    val items: List<Order>? = response.body()
                    view.onOrdersResult(items!!)
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