package ru.dogwalk.performer.UI.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import ru.dogwalk.performer.Model.Settings
import ru.dogwalk.performer.R
import ru.dogwalk.performer.UI.CallBackView.HostActivityListener

class MainActivity : AppCompatActivity(), HostActivityListener {
    private val CONTAINER: Int = R.id.fl_container
    private var navController: NavController? = null
    private var bottomPanel: BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        bottomPanel = findViewById(R.id.bottom_navigation)
        bottomPanel?.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.item_order -> {
                    navController!!.navigate(R.id.ordersFragment)
                    supportActionBar?.title = "Заказы"
                    true
                }
                R.id.item_calendar -> {
                    navController!!.navigate(R.id.calendarFragment)
                    supportActionBar?.title = "Расписание"
                    true
                }
                R.id.item_chat -> {
                    navController!!.navigate(R.id.chatFragment)
                    supportActionBar?.title = "Связаться"
                    true
                }
                R.id.item_profile -> {
                    navController!!.navigate(R.id.profileFragment)
                    supportActionBar?.title = "Профиль"
                    true
                }
                else -> {
                    true
                }
            }
        }
        val settings = Settings(applicationContext)
        val isLoginSuccess = settings.getLogin()?.isNotEmpty()!!
        showBottomPanel(isLoginSuccess)
    }

    override fun showBottomPanel(isShow: Boolean) {
        if (isShow) {
            bottomPanel?.visibility = View.VISIBLE
            bottomPanel?.selectedItemId = R.id.item_order
            navController!!.navigate(R.id.ordersFragment)
            supportActionBar?.title = "Заказы"
        } else {
            bottomPanel?.visibility = View.GONE
            navController!!.navigate(R.id.loginFragment)
            supportActionBar?.title = "Авторизация"
        }
    }

    override fun onMessage(message: String) {
        Snackbar.make(window.decorView, message, Snackbar.LENGTH_SHORT)
            .show()
    }
}
