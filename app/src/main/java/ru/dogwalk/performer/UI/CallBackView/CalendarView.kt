package ru.dogwalk.performer.UI.CallBackView

import ru.dogwalk.performer.Model.ClientOrder

interface CalendarView {
    fun onOrdersScheduleLoad(items: List<ClientOrder>)
    fun onError(message: String)
}