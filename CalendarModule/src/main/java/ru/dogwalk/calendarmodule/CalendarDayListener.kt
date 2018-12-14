package ru.dogwalk.calendarmodule

import java.util.*

internal interface CalendarDayListener {
    fun onDaySelected(selectedDay: Calendar, position: List<Int>)
}