package ru.dogwalk.performer.UI.Adapter

import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class CalendarAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    private val items: MutableList<Fragment> = ArrayList()

    override fun getItem(position: Int): Fragment {
        return items[position]
    }

    fun addItem(fragment: Fragment) {
        items.add(fragment)
    }

    fun removeItem(fragment: Fragment) {
        items.remove(fragment)
    }

    fun removeItem(index: Int) {
        items.removeAt(index)
    }

    override fun getCount(): Int {
        return items.size
    }
}