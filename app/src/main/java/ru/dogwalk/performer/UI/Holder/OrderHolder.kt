package ru.dogwalk.performer.UI.Holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.dogwalk.performer.Model.Order
import ru.dogwalk.performer.R

class OrderHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var ivLogo: ImageView? = null
    private var tvPrice: TextView? = null
    private var tvTitle: TextView? = null
    private var tvSubTitle: TextView? = null
    private var tvDateTime: TextView? = null
    private var tvAddress: TextView? = null
    private var tvMetro: TextView? = null

    init {
        ivLogo = itemView.findViewById(R.id.iv_logo)
        tvPrice = itemView.findViewById(R.id.tv_price)
        tvTitle = itemView.findViewById(R.id.tv_title)
        tvSubTitle = itemView.findViewById(R.id.tv_sub_title)
        tvDateTime = itemView.findViewById(R.id.tv_date_time)
        tvAddress = itemView.findViewById(R.id.tv_address)
        tvMetro = itemView.findViewById(R.id.tv_metro)
    }

    fun setData(order: Order) {
        tvPrice?.text = order.price.toString()
        tvTitle?.text = order.title
        tvSubTitle?.text = order.sub_title
        tvDateTime?.text = order.date_time
        tvAddress?.text = order.address
        tvAddress?.text = order.metro
    }
}