package ru.dogwalk.performer.UI.Adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.dogwalk.performer.Model.Order
import ru.dogwalk.performer.UI.Holder.OrderHolder
import android.view.LayoutInflater
import android.view.View
import ru.dogwalk.performer.R
import ru.dogwalk.performer.UI.CallBackView.OrderClickListener

class OrderListAdapter(private val items: List<Order>) : RecyclerView.Adapter<OrderHolder>() {
    private var orderClickListener: OrderClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_client_order, null)
        return OrderHolder(view)
    }

    fun setClickListener(clickListener: OrderClickListener) {
        orderClickListener = clickListener
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: OrderHolder, position: Int) {
        val item: Order = items[position]
        holder.setClickListener(View.OnClickListener {
            orderClickListener?.onOrderClick(item)
        })
        holder.setData(item)
    }
}