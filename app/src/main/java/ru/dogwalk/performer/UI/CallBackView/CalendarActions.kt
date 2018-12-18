package ru.dogwalk.performer.UI.CallBackView

import ru.dogwalk.performer.Model.ClientOrder
import java.util.*

interface CalendarActions {
    fun onDateSelected(dayDate: Calendar, isSameMonth: Boolean, items: List<ClientOrder>)
    fun onMonthChanged()
}