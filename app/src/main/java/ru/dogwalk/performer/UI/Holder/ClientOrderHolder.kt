package ru.dogwalk.performer.UI.Holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.dogwalk.performer.Model.ClientOrder
import ru.dogwalk.performer.R

class ClientOrderHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var tvServiceTitle: TextView? = null
    private var tvServiceDate: TextView? = null
    private var tvMetro: TextView? = null

    init {
        tvServiceTitle = itemView.findViewById(R.id.tv_service_title)
        tvServiceDate = itemView.findViewById(R.id.tv_service_date)
        tvMetro = itemView.findViewById(R.id.tv_metro)
    }


    fun setClickListener(onClickListener: View.OnClickListener) {
        itemView.setOnClickListener(onClickListener)
    }

    fun setData(item: ClientOrder) {
        tvServiceTitle?.text = item.title
        tvServiceDate?.text = item.begins_at + " - " + item.ends_at
    }
}