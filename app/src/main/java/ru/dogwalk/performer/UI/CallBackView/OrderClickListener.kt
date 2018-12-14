package ru.dogwalk.performer.UI.CallBackView

import ru.dogwalk.performer.Model.Order

interface OrderClickListener {
    fun onOrderClick(order: Order?)
}