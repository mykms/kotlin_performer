package ru.dogwalk.performer.UI.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import ru.dogwalk.performer.Common.Constants
import ru.dogwalk.performer.Model.ClientOrder
import ru.dogwalk.performer.Network.ApiMethods
import ru.dogwalk.performer.Presenter.CalendarPresenter
import ru.dogwalk.performer.R
import ru.dogwalk.performer.UI.Adapter.ClientOrderListAdapter
import ru.dogwalk.performer.UI.CallBackView.CalendarActions
import ru.dogwalk.performer.UI.CallBackView.CalendarView
import ru.dogwalk.performer.UI.CallBackView.ClientOrderClickListener
import ru.dogwalk.performer.UI.Widget.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CalendarFragment : Fragment(), CalendarActions,
    CalendarView, ClientOrderClickListener {
    private var dateLong = 0L
    private var calendar: CalendarDog? = null
    private var rvOrders: RecyclerView? = null

    companion object {
        fun getInstance(calendarDate: Long) : CalendarFragment {
            val args = Bundle()
            args.putLong(Constants.EXTRA_CALENDAR_DATE, calendarDate)

            val fragment = CalendarFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dateLong = arguments?.getLong(Constants.EXTRA_CALENDAR_DATE, 0L)!!
        initPresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_calendar, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        calendar = view.findViewById<CalendarDog>(R.id.cd_calendar) as CalendarDog
        calendar?.setCalendarActions(this)

        rvOrders = view.findViewById<RecyclerView>(R.id.rc_orders_performer)
        rvOrders?.layoutManager = LinearLayoutManager(activity!!)
    }

    private fun initPresenter() {
        val presenter = CalendarPresenter(this, ApiMethods.getInstance(context!!))

        val minMax = getMinMaxStringDates(dateLong)
        presenter.getOrderSchedule(minMax[0], minMax[1])
    }

    private fun getMinMaxStringDates(anyDateInMonth: Long): List<String> {
        val minCalendar = Calendar.getInstance()
        val maxCalendar = Calendar.getInstance()
        minCalendar.timeInMillis = anyDateInMonth
        maxCalendar.timeInMillis = anyDateInMonth
        minCalendar.set(Calendar.DAY_OF_MONTH, minCalendar.getActualMinimum(Calendar.DAY_OF_MONTH))
        maxCalendar.set(Calendar.DAY_OF_MONTH, minCalendar.getActualMaximum(Calendar.DAY_OF_MONTH))

        val format = SimpleDateFormat(Constants.DATE_TIME_PATTERN, Locale.getDefault())
        val minMaxDates = ArrayList<String>()
        minMaxDates.add(format.format(Date(minCalendar.timeInMillis)))
        minMaxDates.add(format.format(Date(maxCalendar.timeInMillis)))

        return minMaxDates
    }

    override fun onDateSelected(dayDate: Calendar, isSameMonth: Boolean, items: List<ClientOrder>) {
        if (items.isNotEmpty()) {
            val adapter = ClientOrderListAdapter(items)
            adapter.setClickListener(this)
            rvOrders?.adapter = adapter
        }
    }

    override fun onMonthChanged() {
        //
    }

    override fun onOrdersScheduleLoad(items: List<ClientOrder>) {
        calendar?.onDrawCalendar(dateLong, items)
    }

    override fun onError(message: String) {
        Snackbar.make(activity?.window?.decorView!!, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun onOrderClick(item: ClientOrder) {
        //
    }
}