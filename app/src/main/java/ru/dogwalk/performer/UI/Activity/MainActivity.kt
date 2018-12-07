package ru.dogwalk.performer.UI.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import ru.dogwalk.performer.R
import ru.dogwalk.performer.UI.CallBackView.LoginListener

class MainActivity : AppCompatActivity(), LoginListener {
    private val CONTAINER: Int = R.id.fl_container
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        navController!!.navigate(R.id.loginFragment)

        val bottomPanel: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomPanel.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.item_order -> {
                    navController!!.navigate(R.id.ordersFragment)
                    true
                }
                R.id.item_calendar -> {
                    navController!!.navigate(R.id.calendarFragment)
                    true
                }
                R.id.item_chat -> {
                    navController!!.navigate(R.id.chatFragment)
                    true
                }
                R.id.item_profile -> {
                    navController!!.navigate(R.id.profileFragment)
                    true
                }
                else -> {
                    true
                }
            }
        }
    }

    override fun onMessage(message: String) {
        Snackbar.make(window.decorView, message, Snackbar.LENGTH_SHORT)
            .show()
    }
}
