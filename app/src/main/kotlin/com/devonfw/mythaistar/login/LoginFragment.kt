package com.devonfw.mythaistar.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.devonfw.mythaistar.R
import com.devonfw.mythaistar.base.BaseFragment
import com.devonfw.mythaistar.common.bindView
import com.devonfw.mythaistar.login.mvp.LoginPresenterImpl
import com.devonfw.mythaistar.login.mvp.LoginUiImpl
import com.devonfw.mythaistar.main.MainComponent
import com.devonfw.mythaistar.user.User

/**
 * Created by MGWIZDAL on 2018-03-09.
 */
const val EXTRA_TOKEN = "authorizationToken"

class LoginFragment : BaseFragment<MainComponent, LoginUiImpl, LoginPresenterImpl>(), LoginUiImpl {

    override val layoutId = R.layout.fragment_login
    override val ui = this

    private val nameEditText: EditText by bindView(R.id.name_et)
    private val passwordEditText: EditText by bindView(R.id.password_et)
    private val loginButton: Button by bindView(R.id.email_sign_in_button)
    lateinit var name: String
    lateinit var password: String
    override fun onViewCreated(view: View, inState: Bundle?) {
        super.onViewCreated(view, inState)
        loginButton.setOnClickListener {
            name = nameEditText.text.toString().trim()
            password = passwordEditText.text.toString().trim()
            val user = User(name, password)
            presenter.startCall(retrofit, user)
        }
    }

    override fun inject(component: MainComponent) {
        component.inject(this)
    }

    override fun onLoginAttemptFail() {
        nameEditText.error = getString(R.string.wrong_name_msg)
        passwordEditText.error = getString(R.string.wrong_password_msg)
    }


    override fun onConnectionFail() {
        Toast.makeText(activity.applicationContext, getString(R.string.connection_problem), Toast.LENGTH_LONG).show()
    }

    override fun showLoggedActivity(token: String?) {
        val intent = Intent(activity, LoggedActivity::class.java).apply {
            putExtra(EXTRA_TOKEN, token)
        }
        startActivity(intent)
    }

}