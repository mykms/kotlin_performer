package ru.dogwalk.performer.UI.Widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import ru.dogwalk.performer.Common.Constants
import ru.dogwalk.performer.Model.ClientOrder
import ru.dogwalk.performer.R
import ru.dogwalk.performer.UI.CallBackView.CalendarActions
import ru.dogwalk.performer.UI.CallBackView.CalendarDayListener
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CalendarDog(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs),
    CalendarDayListener {
    private var locale: Locale = Locale.getDefault()
    private val DAYS = 7
    private val WEEKS_MAX = 4 + 2
    private val weekList: MutableList<CalendarWeek> = ArrayList()
    private var calendarActions: CalendarActions? = null
    private var lastSelectedDay: CalendarDay? = null

    init {
        val view = LayoutInflater.from(getContext()).inflate(R.layout.calendar_layout, this)

        Locale.setDefault(Locale("ru-RU"))
        locale = Locale.getDefault()

        for (i in 1..6) {
            val weekName = "week_$i"
            val week: CalendarWeek = view.findViewWithTag<CalendarWeek>(weekName)
            weekList.add(i - 1, week)
        }
    }

    fun setCalendarActions(actionListener: CalendarActions) {
        this.calendarActions = actionListener
    }

    fun getLastSelectedDay(): Calendar {
        return if (lastSelectedDay == null) {
            Calendar.getInstance()
        } else {
            lastSelectedDay?.getDayDate()!!
        }
    }

    fun onDrawCalendar(dateForDraw: Long, items: List<ClientOrder>) {
        val calendar = Calendar.getInstance(locale)
        calendar.time = Date(dateForDraw)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        val firstDayOfMonth = calendar.get(Calendar.DAY_OF_WEEK)
        var dayOfMonthIndex = getWeekIndex(firstDayOfMonth, calendar)
        val startCalendar = calendar.clone() as Calendar

        startCalendar.add(Calendar.DATE, -(dayOfMonthIndex - 1))

        for (i in 1..WEEKS_MAX) {
            val days: List<CalendarDay> = weekList[i - 1].getDays()
            for (j in 1..DAYS) {
                days[j - 1].setDayDate(startCalendar)
                days[j - 1].setPosition(i - 1, j - 1)
                days[j - 1].setDefaultDay()
                days[j - 1].setDayClickListener(this)
                val dayItems = ArrayList<ClientOrder>()
                for (k in 0 until items.size) {
                    val format = SimpleDateFormat(Constants.DATE_TIME_PATTERN, Locale.getDefault())
                    try {
                        val netDate: Date = format.parse(items[k].begins_at.substring(0, 10))
                        if (format.format(startCalendar.time) == format.format(netDate)) {
                            days[j - 1].setActivityDay(true)
                            dayItems.add(items[k])
                        }
                    } catch (ex: Exception) {
                        ex.printStackTrace()
                    }
                }
                days[j - 1].setDayItems(dayItems)
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

    override fun onDaySelected(selectedDay: Calendar, position: List<Int>, curDay: CalendarDay) {
        lastSelectedDay = if (lastSelectedDay == null) {
            curDay
        } else {
            val lasPosition = lastSelectedDay?.getPosition()
            val lastDay = weekList[lasPosition!![0]].getDayAtPosition(lasPosition[1])
            lastDay.setSelectedDay(false)
            lastSelectedDay?.setDay(lastDay)
            lastSelectedDay?.setSelectedDay(false)
            weekList[lasPosition[0]].setDayAtPosition(lasPosition[1], lastSelectedDay!!)
            curDay
        }
        calendarActions?.onDateSelected(curDay.getDayDate(), curDay.isActive(), curDay.getItems())
    }
}