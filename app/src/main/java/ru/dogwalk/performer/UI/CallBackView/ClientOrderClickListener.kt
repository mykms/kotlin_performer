package ru.dogwalk.performer.UI.CallBackView

import ru.dogwalk.performer.Model.ClientOrder

interface ClientOrderClickListener {
    fun onOrderClick(item: ClientOrder)
}