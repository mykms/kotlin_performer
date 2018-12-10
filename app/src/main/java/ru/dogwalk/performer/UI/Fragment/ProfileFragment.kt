package ru.dogwalk.performer.UI.Fragment

import android.os.Bundle
import android.view.View
import android.widget.Button
import ru.dogwalk.performer.Model.Settings
import ru.dogwalk.performer.R

class ProfileFragment : BaseFragment() {
    private var btLogout: Button? = null

    override fun getArgs(args: Bundle?) {
        // nothing
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_profile
    }

    override fun initComponents(view: View) {
        initViews(view)
    }

    private fun initViews(view: View) {
        btLogout = view.findViewById(R.id.bt_logout)
        btLogout?.setOnClickListener {
            Settings(view.context).logout()
            super.setBottomPanelVisibility(false)
        }
    }
}