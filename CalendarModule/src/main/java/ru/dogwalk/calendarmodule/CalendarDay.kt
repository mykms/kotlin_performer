package ru.dogwalk.calendarmodule

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.calendar_day.view.*
import java.util.*

class CalendarDay(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    private var isSelectedDay = false
    private var isTodayDay = false
    private var dayValue = 0
    private var dayCalendar = Calendar.getInstance()

    init {
        val view = LayoutInflater.from(getContext()).inflate(R.layout.calendar_day, this)
        tv_day.text = dayValue.toString()
        cl_group.setOnClickListener {
            setSelectedDay(true)
        }
    }

    fun setSelectedDay(isSelected: Boolean) {
        isSelectedDay = isSelected
        cl_group.background = setDrawable()
    }

    fun setCurrentDay(isCurrent: Boolean) {
        isTodayDay = isCurrent
        cl_group.background = setDrawable()
    }

    fun setDay(day: Calendar) {
        val dateValue = day.get(Calendar.DAY_OF_MONTH)
        val dayCorrect = if (dateValue <= 0) 1 else if (dateValue > 31) 31 else dateValue
        tv_day.text = dayCorrect.toString()
        dayCalendar = day
        dayValue = dayCorrect
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
        val color = if (isActive) getColorText(R.color.black) else getColorText(R.color.gray)
        tv_day.setTextColor(color)
    }

    fun getDayValue(): Int {
        return dayValue
    }

    fun getDayCalendar(): Calendar {
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