package com.devonfw.mythaistar.login.mvp

import com.devonfw.mythaistar.login.LoginPresenter
import com.devonfw.mythaistar.login.api.LoginClient
import com.devonfw.mythaistar.user.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by MGWIZDAL on 2018-03-22.
 */
class LoginPresenterImpl @Inject constructor() : LoginPresenter<LoginUiImpl>(){
    private lateinit var loginCall: Call<Void>
    fun startCall(retrofit: Retrofit, user: User){
        loginCall = retrofit.create<LoginClient>(LoginClient::class.java).postLogin(user)
        loginCall.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                val resultHeader = response.headers().get("Authorization")
                if(!resultHeader.isNullOrEmpty()){
                    view?.showLoggedActivity(resultHeader)
                }else{
                    view?.onLoginAttemptFail()
                }
            }
            override fun onFailure(call: Call<Void>?, t: Throwable?) {
                view?.onConnectionFail()
            }
        })
    }
}