package com.devonfw.mythaistar.login

import com.devonfw.mythaistar.user.User

/**
 * Created by MGWIZDAL on 2018-03-09.
 */
interface LoginUi {
    fun showLoggedActivity(token : String?)
    fun onLoginAttemptFail()
    fun onConnectionFail()
}