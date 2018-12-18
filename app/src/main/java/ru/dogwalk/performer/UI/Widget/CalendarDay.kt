package ru.dogwalk.performer.UI.Widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.calendar_day.view.*
import ru.dogwalk.performer.Model.ClientOrder
import ru.dogwalk.performer.R
import ru.dogwalk.performer.UI.CallBackView.CalendarDayListener
import java.util.*
import kotlin.collections.ArrayList

class CalendarDay(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    private var isSelectedDay = false
    private var isActive = false
    private var isTodayDay = false
    private var dayValue = 0
    private var dayCalendar = Calendar.getInstance()
    private var dayClickListener: CalendarDayListener? = null
    private var position = ArrayList<Int>(2)
    private var items = ArrayList<ClientOrder>()

    init {
        LayoutInflater.from(getContext()).inflate(R.layout.calendar_day, this)
        tv_day.text = dayValue.toString()
        cl_group.setOnClickListener {
            setSelectedDay(true)
            dayClickListener?.onDaySelected(dayCalendar, position, this)
        }
    }

    fun setDayClickListener(clickListener: CalendarDayListener) {
        this.dayClickListener = clickListener
    }

    fun setSelectedDay(isSelected: Boolean) {
        isSelectedDay = isSelected
        cl_group.background = setDrawable()
    }

    fun setCurrentDay(isCurrent: Boolean) {
        isTodayDay = isCurrent
        cl_group.background = setDrawable()
    }

    fun setDayDate(dayDate: Calendar) {
        val dateValue = dayDate.get(Calendar.DAY_OF_MONTH)
        val dayCorrect = if (dateValue <= 0) 1 else if (dateValue > 31) 31 else dateValue
        tv_day.text = dayCorrect.toString()
        dayCalendar.time = Date(dayDate.timeInMillis)
        dayValue = dayCorrect
    }

    fun setDay(day: CalendarDay) {
        isSelectedDay = day.isSelectedDay
        isTodayDay = day.isTodayDay
        dayValue = day.dayValue
        dayCalendar = day.dayCalendar
        dayClickListener = day.dayClickListener
        position = day.position
        isActive = day.isActive
    }

    fun setPosition(weekPosition: Int, dayPosition: Int) {
        position.add(weekPosition)
        position.add(dayPosition)
    }

    fun setDefaultDay() {
        isTodayDay = false
        isSelectedDay = false
        setActivityDay(false)
        setActiveDay(true)
        cl_group.background = setDrawable()
    }

    /**
     * Активный - день находится в текущем месяце (церный цвет), иначе - не активный (серый)
     */
    fun setActiveDay(isActive: Boolean) {
        this.isActive = isActive
        val color = if (isActive) getColorText(R.color.black) else getColorText(R.color.gray)
        tv_day.setTextColor(color)
    }

    fun setDayItems(dayItems: List<ClientOrder>) {
        this.items.addAll(dayItems)
    }

    fun getDayValue(): Int {
        return dayValue
    }

    fun getDayDate(): Calendar {
        return dayCalendar
    }

    /**
     * Есть активности (события) в этом дне?
     */
    fun setActivityDay(isActivity: Boolean) {
        view_dot.visibility = if (isActivity) View.VISIBLE else View.GONE
    }

    private fun setDrawable() : Drawable {
        return if (isSelectedDay && isTodayDay) {
            getDrawableImg(R.drawable.day_current_selected)
        } else if (isSelectedDay) {
            getDrawableImg(R.drawable.day_selected)
        } else if (isTodayDay) {
            getDrawableImg(R.drawable.day_current)
        } else {
            getDrawableImg(R.drawable.day_default)
        }
    }

    fun isSelectedDay(): Boolean {
        return isSelectedDay
    }

    fun isToday(): Boolean {
        return isTodayDay
    }

    fun isActive(): Boolean {
        return isActive
    }

    fun getDayClickListener(): CalendarDayListener? {
        return dayClickListener
    }

    fun getPosition(): ArrayList<Int> {
        return position
    }

    fun getItems(): List<ClientOrder> {
        return items
    }

    private fun getDrawableImg(imgResource: Int) : Drawable {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            resources.getDrawable(imgResource, null)
        } else {
            resources.getDrawable(imgResource)
        }
    }

    private fun getColorText(imgResource: Int) : Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            resources.getColor(imgResource, null)
        } else {
            resources.getColor(imgResource)
        }
    }
}