package ru.dogwalk.calendarmodule

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import java.util.*
import kotlin.collections.ArrayList

class CalendarDog(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    private var locale: Locale = Locale.getDefault()
    private var currentCalendar: Calendar = Calendar.getInstance(locale)
    private val DAYS = 7
    private val WEEKS_MAX = 4 + 2
    private val DAYS_MAX = WEEKS_MAX * DAYS
    private val weekList: MutableList<CalendarWeek> = ArrayList()

    init {
        val view = LayoutInflater.from(getContext()).inflate(R.layout.calendar_layout, this)

        Locale.setDefault(Locale("ru-RU"))
        locale = Locale.getDefault()

        for (i in 1..6) {
            val weekName = "week_$i"
            val week: CalendarWeek = view.findViewWithTag<CalendarWeek>(weekName)
            weekList.add(i - 1, week)
        }
        setDaysInCalendar()
    }

    private fun setDaysInCalendar() {
        val calendar = Calendar.getInstance(locale)
        calendar.time = currentCalendar.time
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        val firstDayOfMonth = calendar.get(Calendar.DAY_OF_WEEK)
        var isEndPosition = false
        var dayOfMonthIndex = getWeekIndex(firstDayOfMonth, calendar)
        val actualMaximum = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        val startCalendar = calendar.clone() as Calendar

        startCalendar.add(Calendar.DATE, -(dayOfMonthIndex - 1))
        val monthEndIndex = 42 - (actualMaximum + dayOfMonthIndex - 1)

        for (i in 1..WEEKS_MAX) {
            val days: List<CalendarDay> = weekList[i - 1].getDays()
            for (j in 1..DAYS) {
                days[j - 1].setDay(startCalendar)
                days[j - 1].setDefaultDay()
                if (CalendarUtils.isSameMonth(calendar, startCalendar)) {
                    if (CalendarUtils.isToday(startCalendar)) {
                        days[j - 1].setCurrentDay(true)
                    }
                    isEndPosition = true
                } else {
                    days[j - 1].setActiveDay(false)
                    if (!isEndPosition) {
                        //dayOfMonthContainer.setOnClickListener(onDayOfMonthClickListenerPrev)
                    } else {
                        //dayOfMonthContainer.setOnClickListener(onDayOfMonthClickListenerNext)
                    }
//                    if (!isOverflowDateVisible())
//                        dayView!!.setVisibility(View.GONE)
//                    else if (i >= 36 && monthEndIndex.toFloat() / 7.0f >= 1) {
//                        dayView!!.setVisibility(View.GONE)
//                    }
                }
                startCalendar.add(Calendar.DATE, 1)
                dayOfMonthIndex++
            }
        }
        // Если в последнем блоке недель нет видимых дней, то скроем полность блок
    }


    private fun getWeekIndex(weekIndex: Int, currentCalendar: Calendar): Int {
        val firstDayWeekPosition = currentCalendar.firstDayOfWeek
        return when {
            firstDayWeekPosition == 1 -> weekIndex
            weekIndex == 1 -> 7
            else -> weekIndex - 1
        }
    }
}