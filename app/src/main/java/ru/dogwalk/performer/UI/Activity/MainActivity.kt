package ru.dogwalk.performer.UI.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import ru.dogwalk.performer.Common.Constants
import ru.dogwalk.performer.Model.Settings
import ru.dogwalk.performer.R
import ru.dogwalk.performer.UI.CallBackView.HostActivityListener

class MainActivity : AppCompatActivity(), HostActivityListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottom_navigation.setupWithNavController(nav_host_fragment.findNavController())
        showBottomPanel(!Settings(applicationContext).getLogin().isNullOrEmpty())
    }

    override fun showBottomPanel(isShow: Boolean) {
        bottom_navigation?.visibility = if (isShow) View.VISIBLE else View.GONE
        nav_host_fragment.findNavController().navigate(if (isShow) R.id.ordersFragment else R.id.loginFragment)
        supportActionBar?.title = if (isShow) "Заказы" else "Авторизация"
    }

    override fun onMessage(message: String) {
        Snackbar.make(window.decorView, message, Snackbar.LENGTH_SHORT)
            .show()
    }

//    override fun onSupportNavigateUp(): Boolean {
//        setVisibilityBackButton(false)
//        navController?.navigateUp()
//        return super.onSupportNavigateUp()
//    }
//
//    override fun onBackPressed() {
//        setVisibilityBackButton(false)
//        navController?.navigateUp()
//    }
//


//    override fun navigateTo(resourceId: Int, args: Bundle?) {
//        when(resourceId) {
//            R.id.orderDetailFragment -> {
//                navController!!.navigate(R.id.orderDetailFragment)
//                val id = args?.getLong(Constants.EXTRA_ORDER_ID, 0L)
//                supportActionBar?.title = "Заказ № $id"
//                setVisibilityBackButton(true)
//            }
//            else -> {
//                navController!!.navigate(R.id.ordersFragment)
//                supportActionBar?.title = "Заказы"
//            }
//        }
//    }
//
//    private fun setVisibilityBackButton(isVisible: Boolean) {
//        supportActionBar?.setDisplayHomeAsUpEnabled(isVisible)
//        supportActionBar?.setDisplayShowHomeEnabled(isVisible)
//    }
}
