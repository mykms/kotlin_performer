package ru.dogwalk.performer.UI.Widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import ru.dogwalk.performer.R

class CalendarWeek(context: Context?, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {
    private var days: MutableList<CalendarDay> = ArrayList()

    init {
        val view = LayoutInflater.from(getContext()).inflate(R.layout.calendar_layout_week, this)
        days.add(view.findViewById(R.id.tv_d1))
        days.add(view.findViewById(R.id.tv_d2))
        days.add(view.findViewById(R.id.tv_d3))
        days.add(view.findViewById(R.id.tv_d4))
        days.add(view.findViewById(R.id.tv_d5))
        days.add(view.findViewById(R.id.tv_d6))
        days.add(view.findViewById(R.id.tv_d7))
    }

    fun getDays() : List<CalendarDay> {
        return days
    }

    fun getDayAtPosition(indexDay: Int): CalendarDay {
        return days[indexDay]
    }

    fun setDayAtPosition(indexDay: Int, day: CalendarDay) {
        days[indexDay] = day
    }

    fun getDayAtValue(dayValue: Int) : CalendarDay? {
        for (day in days) {
            if (day.getDayValue() == dayValue)
                return day
        }
        return null
    }

    fun setDays(newDays: List<CalendarDay>) {
        days.clear()
        days.addAll(newDays)
    }

    fun setDay(indexDay: Int, day: CalendarDay) {
        days[indexDay] = day
    }

    fun setSelectedDay(indexDay: Int) {
        val day = days[indexDay]
        day.setSelectedDay(true)
        days[indexDay] = day
    }
}