package com.devonfw.mythaistar.login.api


import com.devonfw.mythaistar.user.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * Created by MGWIZDAL on 2018-03-22.
 */
interface LoginClient {
    @Headers("Content-type: application/json")
    @POST("/mythaistar/login")
    fun postLogin( @Body user : User): Call<Void>
}