package ru.dogwalk.performer.View

import ru.dogwalk.performer.Model.Client

interface UserProfileView : BaseView {
    fun onUserInfo(userSession: Client?)
}