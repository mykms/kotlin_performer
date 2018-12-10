package ru.dogwalk.performer.UI.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import ru.dogwalk.performer.Model.TabFragmentTitle
import ru.dogwalk.performer.R
import ru.dogwalk.performer.UI.Adapter.TabAdapter

class OrdersFragment : Fragment() {
    private var tabs: TabLayout? = null
    private var vpOrders: ViewPager? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_orders, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        tabs = view.findViewById(R.id.tabs)
        vpOrders = view.findViewById(R.id.view_pager)

        val adapter =  TabAdapter(childFragmentManager)
        adapter.addItem(TabFragmentTitle(OrderListFragment.getInstance("hot"), "Горячие"))
        adapter.addItem(TabFragmentTitle(OrderListFragment.getInstance("all"), "Все"))
        adapter.addItem(TabFragmentTitle(OrderListFragment.getInstance("response"), "Отклики"))

        vpOrders?.adapter = adapter
        tabs?.setupWithViewPager(vpOrders)
        vpOrders?.currentItem = 0
    }
}