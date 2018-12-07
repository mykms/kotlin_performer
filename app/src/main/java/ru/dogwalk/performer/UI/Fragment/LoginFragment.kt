package ru.dogwalk.performer.UI.Fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import ru.dogwalk.performer.Model.Settings
import ru.dogwalk.performer.Network.ApiMethods
import ru.dogwalk.performer.Presenter.LoginPresenter
import ru.dogwalk.performer.R
import ru.dogwalk.performer.UI.CallBackView.LoginListener
import ru.dogwalk.performer.View.LoginView
import ru.dogwalk.progressbardog.ProgressBarDog

class LoginFragment : Fragment(), LoginView {
    private var etLogin: EditText? = null
    private var etPassword: EditText? = null
    private var btLogin: Button? = null
    private var pbDog: ProgressBarDog? = null
    private var activityListener: LoginListener? = null
    private var loginPresenter: LoginPresenter? = null

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        if (activity is LoginListener) {
            activityListener = activity
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is LoginListener) {
            activityListener = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginPresenter = LoginPresenter(this, ApiMethods.getInstance(context!!))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        onActionsInit()
    }

    private fun initViews(view: View) {
        btLogin = view.findViewById(R.id.bt_login)
        etLogin = view.findViewById(R.id.et_login)
        etPassword = view.findViewById(R.id.et_password)
        pbDog = view.findViewById(R.id.pb_dog)
    }

    private fun onActionsInit() {
        btLogin?.setOnClickListener {
            loginPresenter?.login(etLogin?.text.toString(), etPassword?.text.toString())
        }
        pbDog?.stopProgress()
    }

    override fun onLoginResult(isSuccess: Boolean, login: String, token: String) {
        val settings = Settings(context!!)
        settings.saveAuthData(login, token)
        onError("Успешно вошли как: $login с токеном $token")
    }

    override fun onError(message: String) {
        activityListener?.onMessage(message)
    }

    override fun onProgress(isProgress: Boolean) {
        if (isProgress) {
            pbDog?.startProgress()
        } else {
            pbDog?.stopProgress()
        }
    }

    override fun onDetach() {
        super.onDetach()
        activityListener = null
    }
}