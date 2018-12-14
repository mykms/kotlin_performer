package ru.dogwalk.performer.UI.Fragment

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import ru.dogwalk.performer.Model.Client
import ru.dogwalk.performer.Model.Settings
import ru.dogwalk.performer.Network.ApiMethods
import ru.dogwalk.performer.Presenter.UserProfilePresenter
import ru.dogwalk.performer.R
import ru.dogwalk.performer.View.UserProfileView
import ru.dogwalk.progressbardog.ProgressBarDog

class ProfileFragment : BaseFragment(), UserProfileView {
    private var btLogout: Button? = null
    private var loginPresenter: UserProfilePresenter? = null
    private var pbDog: ProgressBarDog? = null
    private var tvName: TextView? = null
    private var tvPhone: TextView? = null

    override fun getArgs(args: Bundle?) {
        // nothing
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_profile
    }

    override fun initComponents(view: View) {
        initViews(view)
        loginPresenter = UserProfilePresenter(this, ApiMethods.getInstance(context!!))
        loginPresenter?.getUserInfo()
    }

    private fun initViews(view: View) {
        tvName = view.findViewById(R.id.tv_name)
        tvPhone = view.findViewById(R.id.tv_phone)

        btLogout = view.findViewById(R.id.bt_logout)
        btLogout?.setOnClickListener {
            Settings(view.context).logout()
            super.setBottomPanelVisibility(false)
        }
        pbDog = view.findViewById(R.id.pb_dog)
        pbDog?.stopProgress()
    }

    override fun onUserInfo(userSession: Client?) {
        tvName?.text = userSession?.full_name
        tvPhone?.text = userSession?.phone
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
}