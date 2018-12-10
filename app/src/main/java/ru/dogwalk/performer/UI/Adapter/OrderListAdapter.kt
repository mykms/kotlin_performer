package ru.dogwalk.performer.UI.Adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.dogwalk.performer.Model.Order
import ru.dogwalk.performer.UI.Holder.OrderHolder
import android.view.LayoutInflater
import ru.dogwalk.performer.R

class OrderListAdapter(private val items: List<Order>) : RecyclerView.Adapter<OrderHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.order_item, null)
        return OrderHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: OrderHolder, position: Int) {
        val item: Order = items[position]
        holder.setData(item)
    }
}