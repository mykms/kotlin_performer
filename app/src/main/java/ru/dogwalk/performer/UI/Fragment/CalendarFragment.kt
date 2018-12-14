package ru.dogwalk.performer.UI.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.dogwalk.calendarmodule.CalendarActions
import ru.dogwalk.calendarmodule.CalendarDog
import ru.dogwalk.performer.R

class CalendarFragment : Fragment(), CalendarActions {
    private var calendar: CalendarDog? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_calendar, container, false)
        calendar = view.findViewById(R.id.cv_calendar_dog)
        calendar?.setCalendarActions(this)
        return view
    }

    override fun onDateSelected() {
        //
    }

    override fun onMonthChanged() {
        //
    }
}