package ru.dogwalk.performer.UI.Fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.dogwalk.performer.Common.Constants
import ru.dogwalk.performer.Model.Order
import ru.dogwalk.performer.Network.ApiMethods
import ru.dogwalk.performer.Presenter.OrderPresenter
import ru.dogwalk.performer.R
import ru.dogwalk.performer.UI.Adapter.OrderListAdapter
import ru.dogwalk.performer.View.OrderView
import ru.dogwalk.progressbardog.ProgressBarDog

class OrderListFragment : BaseFragment(), OrderView {
    private var rvOrders: RecyclerView? = null
    private var pbDog: ProgressBarDog? = null
    private var orderPresenter: OrderPresenter? = null
    private var orderType = ""

    companion object {
        fun getInstance(orderType: String) : OrderListFragment {
            val args = Bundle()
            args.putString(Constants.EXTRA_ORDER_TYPE, orderType)

            val fragment = OrderListFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun getArgs(args: Bundle?) {
        orderType = args?.getString(Constants.EXTRA_ORDER_TYPE, "")!!
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_order_list
    }

    override fun initComponents(view: View) {
        initViews(view)
        orderPresenter = OrderPresenter(this, ApiMethods.getInstance(context!!))
        orderPresenter?.getOrderList(orderType)
    }

    private fun initViews(view: View) {
        rvOrders = view.findViewById(R.id.rv_orders)
        rvOrders?.layoutManager = LinearLayoutManager(activity)

        pbDog = view.findViewById(R.id.pg_dog)
        pbDog?.stopProgress()
    }

    override fun onError(message: String) {
        super.onMessage(message)
    }

    override fun onProgress(isProgress: Boolean) {
        if (isProgress) {
            pbDog?.startProgress()
        } else {
            pbDog?.stopProgress()
        }
    }

    override fun onOrdersResult(items: List<Order>) {
        rvOrders?.adapter = OrderListAdapter(items)
    }
}