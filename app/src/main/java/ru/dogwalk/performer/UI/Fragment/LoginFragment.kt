package ru.dogwalk.performer.UI.Fragment

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import ru.dogwalk.performer.Model.Settings
import ru.dogwalk.performer.Network.ApiMethods
import ru.dogwalk.performer.Presenter.LoginPresenter
import ru.dogwalk.performer.R
import ru.dogwalk.performer.View.LoginView
import ru.dogwalk.progressbardog.ProgressBarDog

class LoginFragment : BaseFragment(), LoginView {
    private var etLogin: EditText? = null
    private var etPassword: EditText? = null
    private var btLogin: Button? = null
    private var pbDog: ProgressBarDog? = null
    private var loginPresenter: LoginPresenter? = null

    override fun getArgs(args: Bundle?) {
        // nothing
    }

    override fun initComponents(view: View) {
        loginPresenter = LoginPresenter(this, ApiMethods.getInstance(context!!))

        initViews(view)
        onActionsInit()
    }

    override fun setResourceLayout(): Int {
        return R.layout.fragment_login
    }

    private fun initViews(view: View) {
        btLogin = view.findViewById(R.id.bt_login)
        etLogin = view.findViewById(R.id.et_login)
        etPassword = view.findViewById(R.id.et_password)
        pbDog = view.findViewById(R.id.pb_dog)
        pbDog?.stopProgress()
    }

    private fun onActionsInit() {
        btLogin?.setOnClickListener {
            loginPresenter?.login(etLogin?.text.toString(), etPassword?.text.toString())
        }
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

    override fun onLoginResult(isSuccess: Boolean, login: String, token: String) {
        val settings = Settings(context!!)
        settings.saveAuthData(login, token)
        if (isSuccess) {
            super.setBottomPanelVisibility(true)
            onError("Успешно вошли как: $login с токеном $token")
        }
    }
}