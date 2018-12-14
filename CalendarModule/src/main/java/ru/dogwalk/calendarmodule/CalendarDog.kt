package ru.dogwalk.calendarmodule

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import java.util.*
import kotlin.collections.ArrayList

class CalendarDog(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs), CalendarDayListener {
    private var locale: Locale = Locale.getDefault()
    private var currentCalendar: Calendar = Calendar.getInstance(locale)
    private val DAYS = 7
    private val WEEKS_MAX = 4 + 2
    private val weekList: MutableList<CalendarWeek> = ArrayList()
    private var calendarActions: CalendarActions? = null
    private var lastSelectedDay = Calendar.getInstance()    // Последняя выбранная дата
    private var currentSelectedDay = Calendar.getInstance() // Текущая выбранная дата

    init {
        val view = LayoutInflater.from(getContext()).inflate(R.layout.calendar_layout, this)

        Locale.setDefault(Locale("ru-RU"))
        locale = Locale.getDefault()

        for (i in 1..6) {
            val weekName = "week_$i"
            val week: CalendarWeek = view.findViewWithTag<CalendarWeek>(weekName)
            weekList.add(i - 1, week)
        }
        onDrawCalendar(currentCalendar)
    }

    fun setCalendarActions(actionListener: CalendarActions) {
        this.calendarActions = actionListener
    }

    fun getLastSelectedDay(): Calendar {
        return lastSelectedDay
    }

    private fun onDrawCalendar(dateForDraw: Calendar) {
        val calendar = Calendar.getInstance(locale)
        calendar.time = dateForDraw.time
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        val firstDayOfMonth = calendar.get(Calendar.DAY_OF_WEEK)
        var dayOfMonthIndex = getWeekIndex(firstDayOfMonth, calendar)
        val startCalendar = calendar.clone() as Calendar

        startCalendar.add(Calendar.DATE, -(dayOfMonthIndex - 1))

        for (i in 1..WEEKS_MAX) {
            val days: List<CalendarDay> = weekList[i - 1].getDays()
            for (j in 1..DAYS) {
                days[j - 1].setDay(startCalendar)
                days[j - 1].setPosition(i - 1, j - 1)
                days[j - 1].setDefaultDay()
                days[j - 1].setDayClickListener(this)
                setDayStyle(calendar, startCalendar, days[j - 1])
                startCalendar.add(Calendar.DATE, 1)
                dayOfMonthIndex++
            }
        }
    }

    private fun setDayStyle(calendar: Calendar, startCalendar: Calendar, dayForColor: CalendarDay) : CalendarDay {
        if (CalendarUtils.isSameMonth(calendar, startCalendar)) {
            if (CalendarUtils.isToday(startCalendar)) {
                dayForColor.setCurrentDay(true)
            }
        } else {
            dayForColor.setActiveDay(false)
        }

        return dayForColor
    }

    private fun getWeekIndex(weekIndex: Int, currentCalendar: Calendar): Int {
        val firstDayWeekPosition = currentCalendar.firstDayOfWeek
        return when {
            firstDayWeekPosition == 1 -> weekIndex
            weekIndex == 1 -> 7
            else -> weekIndex - 1
        }
    }

    override fun onDaySelected(selectedDay: Calendar, position: List<Int>) {
        lastSelectedDay = currentSelectedDay.clone() as Calendar?

        val tmpDate = selectedDay.clone() as Calendar
        tmpDate.time = Date(selectedDay.timeInMillis)
        currentSelectedDay = tmpDate
        //weekList[position[0]].getDayAtPosition(position[1]).setDefaultDay()

        calendarActions?.onDateSelected()
    }
}