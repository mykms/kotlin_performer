package ru.dogwalk.performer.View

import ru.dogwalk.performer.Model.Order

interface OrderView : BaseView {
    fun onOrdersResult(items: List<Order>)
}