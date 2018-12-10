package ru.dogwalk.performer.Presenter

import ru.dogwalk.performer.Model.Order
import ru.dogwalk.performer.Network.ApiMethods
import ru.dogwalk.performer.View.OrderView

class OrderPresenter(private val view: OrderView, private val apiMethods: ApiMethods) : BasePresenter() {
    fun getOrderList(orderType: String) {
        view.onProgress(true)
        val req = apiMethods.getOrders(orderType)
        view.onOrdersResult(testData())
        view.onProgress(false)
        /*
        req.enqueue {
            onResponse = { response ->
                if (response.code() in 200..299) {
                    val sessions: List<Order>? = response.body()
                    //
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
        */
    }

    private fun testData(): List<Order> {
        val items: MutableList<Order> = ArrayList()

        items.add(Order(1L))
        items.add(Order(2L, "11111", "11111", "11111", "11111", "11111"))
        items.add(Order(3L, "22222", "22222", "22222", "22222", "22222"))
        items.add(Order(4L, "33333", "33333", "33333", "33333", "33333"))
        items.add(Order(5L, "44444", "44444", "44444", "44444", "44444"))
        items.add(Order(6L, "55555", "55555", "55555", "55555", "55555"))
        items.add(Order(7L, "66666", "66666", "66666", "66666", "66666"))

        return items
    }
}