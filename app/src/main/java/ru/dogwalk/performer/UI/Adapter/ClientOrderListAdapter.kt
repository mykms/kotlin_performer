package ru.dogwalk.performer.UI.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.dogwalk.performer.Model.ClientOrder
import ru.dogwalk.performer.R
import ru.dogwalk.performer.UI.CallBackView.ClientOrderClickListener
import ru.dogwalk.performer.UI.Holder.ClientOrderHolder

class ClientOrderListAdapter(private val items: List<ClientOrder>) : RecyclerView.Adapter<ClientOrderHolder>() {
    private var orderClickListener: ClientOrderClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientOrderHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.calendar_order_item, null)
        return ClientOrderHolder(view)
    }

    fun setClickListener(clickListener: ClientOrderClickListener) {
        orderClickListener = clickListener
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ClientOrderHolder, position: Int) {
        val item: ClientOrder = items[position]
        holder.setClickListener(View.OnClickListener {
            orderClickListener?.onOrderClick(item)
        })
        holder.setData(item)
    }
}