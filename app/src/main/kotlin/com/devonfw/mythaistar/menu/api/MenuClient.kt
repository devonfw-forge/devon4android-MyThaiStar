package com.devonfw.mythaistar.menu.api

import com.devonfw.mythaistar.menu.api.models.DishResponseBody
import com.devonfw.mythaistar.menu.api.models.MenuRequest
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by MGWIZDAL on 2018-02-02.
 */
interface MenuClient {
    @GET("/mythaistar/services/rest/dishmanagement/v1/dish/{id}")
    fun getDishById(@Path("id") user: String): Call<MenuClient>

    @Headers("Content-type: application/json")
    @POST("/mythaistar/services/rest/dishmanagement/v1/dish/search")
    fun postAllDishes(@Body menu: MenuRequest): Call<DishResponseBody>
}