package ru.dogwalk.performer.UI.Holder

import android.graphics.drawable.Drawable
import android.os.Build
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
        val img = if (order.is_like) {
            getImg(R.drawable.vector_ic_heart_like_circle)
        } else {
            getImg(R.drawable.vector_ic_dog)
        }
        ivLogo?.setImageDrawable(img)
        tvPrice?.text = order.price.toString()
        tvTitle?.text = order.title
        tvSubTitle?.text = order.subtitle
        tvDateTime?.text = order.starting_at
        tvAddress?.text = order.client.client_street
        tvMetro?.text = order.client.client_metro
    }

    fun setClickListener(clickListener: View.OnClickListener) {
        itemView.setOnClickListener(clickListener)
    }

    private fun getImg(imgResource: Int) : Drawable {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            itemView.resources.getDrawable(imgResource, null)
        } else {
            itemView.resources.getDrawable(imgResource)
        }
    }
}