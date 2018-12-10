package ru.dogwalk.performer.UI.Fragment

import android.app.PendingIntent.getActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import ru.dogwalk.performer.UI.Activity.MainActivity
import org.junit.Rule

class LoginFragmentTest {
    //@Rule
    //var menuActivityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java, true, true)

    @Before
    @Throws(Exception::class)
    fun setUp() {
        //
    }
    @Test
    fun onCreate() {
    }

    @Test
    fun onCreateView() {
        Espresso.onView(ViewMatchers.withId(ru.dogwalk.performer.R.id.et_login)).perform(ViewActions.typeText("my_login"))
        Espresso.onView(ViewMatchers.withId(ru.dogwalk.performer.R.id.et_password)).perform(ViewActions.typeText("12345678"))
        Espresso.onView(ViewMatchers.withId(ru.dogwalk.performer.R.id.bt_login)).perform(ViewActions.click())
    }

    @Test
    fun onLoginResult() {
    }

    @Test
    fun onError() {
    }

    @Test
    fun onProgress() {
    }
}