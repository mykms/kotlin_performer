package ru.dogwalk.calendarmodule

import java.util.*

internal class CalendarUtils {
    companion object {
        fun isSameMonth(c1: Calendar?, c2: Calendar?): Boolean {
            return if (c1 == null || c2 == null) false else {
                c1.get(Calendar.ERA) == c2.get(Calendar.ERA) &&
                c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) &&
                c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)
            }
        }

        // Проверка календаря на Сегодняшний день
        fun isToday(calendar: Calendar): Boolean {
            return isSameDay(calendar, Calendar.getInstance())
        }

        // Сравнение на одинаковый день
        fun isSameDay(cal1: Calendar?, cal2: Calendar?): Boolean {
            if (cal1 == null || cal2 == null)
                throw IllegalArgumentException("The dates must not be null")
            return cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                   cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                   cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)
        }
    }
}