package ru.dogwalk.performer.UI.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import ru.dogwalk.performer.R
import ru.dogwalk.performer.UI.Adapter.CalendarAdapter
import java.util.*

open class CalendarPagerFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_pager_calendar, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        val adapter = CalendarAdapter(childFragmentManager)
        adapter.addItem(CalendarFragment.getInstance(getTimeWithCorrection(-1)))
        adapter.addItem(CalendarFragment.getInstance(getTimeWithCorrection(0)))
        adapter.addItem(CalendarFragment.getInstance(getTimeWithCorrection(1)))

        val vpCalendar = view.findViewById<ViewPager>(R.id.vp_calendar_pager)
        vpCalendar?.adapter = adapter
        vpCalendar.currentItem = 1
    }

    private fun getTimeWithCorrection(correction: Int): Long {
        val curCalendar = Calendar.getInstance()
        curCalendar.add(Calendar.MONTH, correction)
        return curCalendar.timeInMillis
    }
}