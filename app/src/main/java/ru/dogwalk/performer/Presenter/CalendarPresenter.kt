package ru.dogwalk.performer.Presenter

import ru.dogwalk.performer.Model.ClientOrder
import ru.dogwalk.performer.Network.ApiMethods
import ru.dogwalk.performer.UI.CallBackView.CalendarView

class CalendarPresenter constructor(private val view: CalendarView, private val apiMethods: ApiMethods): BasePresenter() {
    fun getOrderSchedule(begins_at_gte: String, ends_at_lte: String) {
        val req = apiMethods.getOrders(begins_at_gte, ends_at_lte)
        req.enqueue {
            onResponse = { response ->
                if (response.code() in 200..299) {
                    var items = ArrayList<ClientOrder>()
                    if (response.body() != null) {
                        items = response.body() as ArrayList<ClientOrder>
                    }
                    view.onOrdersScheduleLoad(items)
                } else {
                    view.onError("Запрос прошел, но есть ошибка ${response.code()}")
                }
                //view.onProgress(false)
            }

            onFailure = { onFailure ->
                view.onError("Сетевая ошибка ${onFailure?.message}")
                //view.onProgress(false)
            }
        }
    }
}