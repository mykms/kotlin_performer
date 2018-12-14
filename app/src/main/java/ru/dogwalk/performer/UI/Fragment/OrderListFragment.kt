package ru.dogwalk.performer.UI.Fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import ru.dogwalk.performer.Common.Constants
import ru.dogwalk.performer.Model.Order
import ru.dogwalk.performer.Network.ApiMethods
import ru.dogwalk.performer.Presenter.OrderPresenter
import ru.dogwalk.performer.R
import ru.dogwalk.performer.UI.Adapter.OrderListAdapter
import ru.dogwalk.performer.UI.CallBackView.OrderClickListener
import ru.dogwalk.performer.View.OrderView
import ru.dogwalk.progressbardog.ProgressBarDog

class OrderListFragment : BaseFragment(), OrderView, SwipeRefreshLayout.OnRefreshListener, OrderClickListener {
    private var rvOrders: RecyclerView? = null
    private var pbDog: ProgressBarDog? = null
    private var swipe: SwipeRefreshLayout? = null
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
        orderType = args?.getString(Constants.EXTRA_ORDER_TYPE, "").toString()
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_order_list
    }

    override fun initComponents(view: View) {
        initViews(view)
        orderPresenter = OrderPresenter(this, ApiMethods.getInstance(context!!))
        sendRequest(orderType)
    }

    private fun sendRequest(oType: String) {
        swipe?.isRefreshing = false
        when(oType) {
            "hot" -> orderPresenter?.getOrderList(true)
            "all" -> orderPresenter?.getOrderList()
            "response" -> orderPresenter?.getOrderList(true)
        }
    }

    private fun initViews(view: View) {
        rvOrders = view.findViewById(R.id.rv_orders)
        rvOrders?.layoutManager = LinearLayoutManager(activity)

        pbDog = view.findViewById(R.id.pg_dog)
        pbDog?.stopProgress()

        swipe = view.findViewById(R.id.swipe)
        swipe?.setOnRefreshListener(this)
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
        val adapter = OrderListAdapter(items)
        adapter.setClickListener(this)
        rvOrders?.adapter = adapter
    }

    override fun onRefresh() {
        sendRequest(orderType)
    }

    override fun onOrderClick(order: Order?) {
        val args = Bundle()
        args.putLong(Constants.EXTRA_ORDER_ID, order?.id!!)
        navigateTo(R.id.orderDetailFragment, args)
    }
}