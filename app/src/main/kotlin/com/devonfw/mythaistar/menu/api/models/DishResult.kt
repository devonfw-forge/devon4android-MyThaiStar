package com.devonfw.mythaistar.menu.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by MGWIZDAL on 2018-02-06.
 */
class DishResult {
    @SerializedName("dish")
    @Expose
    var dish: Dish? = null
    @SerializedName("image")
    @Expose
    var image: DishImage? = null
    /*
    @SerializedName("extras")
    @Expose
    var extras: List<Extra>? = null
    @SerializedName("categories")
    @Expose
    var categories: List<Category>? = null*/
}