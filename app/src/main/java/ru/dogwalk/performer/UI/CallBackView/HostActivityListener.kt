package ru.dogwalk.performer.UI.CallBackView

interface HostActivityListener {
    fun onMessage(message: String)
    fun showBottomPanel(isShow: Boolean)
}