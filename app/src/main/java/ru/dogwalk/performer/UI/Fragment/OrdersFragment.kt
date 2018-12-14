package ru.dogwalk.performer.UI.Fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_orders.*
import kotlinx.android.synthetic.main.fragment_orders.view.*
import ru.dogwalk.performer.Model.TabFragmentTitle
import ru.dogwalk.performer.R
import ru.dogwalk.performer.UI.Adapter.TabAdapter

class OrdersFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_orders, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {
        val adapter = TabAdapter(childFragmentManager)

        adapter.addItem(TabFragmentTitle(OrderListFragment.getInstance("hot"), "Горячие"))
        adapter.addItem(TabFragmentTitle(OrderListFragment.getInstance("all"), "Все"))
        adapter.addItem(TabFragmentTitle(OrderListFragment.getInstance("response"), "Отклики"))

        view.view_pager.adapter = adapter
    }
}