package ru.dogwalk.performer.UI.CallBackView

import ru.dogwalk.performer.UI.Widget.CalendarDay
import java.util.*

interface CalendarDayListener {
    fun onDaySelected(selectedDay: Calendar, position: List<Int>, curDay: CalendarDay)
}