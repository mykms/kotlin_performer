package ru.dogwalk.performer.UI.CallBackView

import android.os.Bundle

interface HostActivityListener {
    fun onMessage(message: String)
    fun showBottomPanel(isShow: Boolean)
//    fun navigateTo(resourceId: Int, args: Bundle?)
}