package ru.dogwalk.performer.UI.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import ru.dogwalk.performer.R
import ru.dogwalk.performer.UI.CallBackView.LoginListener
import ru.dogwalk.performer.UI.Fragment.ChatFragment
import ru.dogwalk.performer.UI.Fragment.LoginFragment

class MainActivity : AppCompatActivity(), LoginListener {
    private val CONTAINER: Int = R.id.fl_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomPanel: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomPanel.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.item1 -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(CONTAINER, ChatFragment())
                        .commit()
                    true
                }
                R.id.item2 -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(CONTAINER, LoginFragment())
                        .commit()
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
