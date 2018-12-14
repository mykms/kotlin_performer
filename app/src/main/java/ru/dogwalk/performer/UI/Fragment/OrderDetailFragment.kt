package ru.dogwalk.performer.UI.Fragment

import android.os.Bundle
import android.view.View
import ru.dogwalk.performer.R
import ru.dogwalk.performer.View.OrderDetailView
import ru.dogwalk.progressbardog.ProgressBarDog

class OrderDetailFragment : BaseFragment(), OrderDetailView {
    private var pbDog: ProgressBarDog? = null

    override fun getArgs(args: Bundle?) {
        // nothing
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_order_detail
    }

    override fun initComponents(view: View) {
        pbDog = view.findViewById(R.id.pb_dog)
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

    override fun onOrderDetail() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}