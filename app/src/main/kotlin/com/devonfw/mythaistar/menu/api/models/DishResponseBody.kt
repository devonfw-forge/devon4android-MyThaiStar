package com.devonfw.mythaistar.menu.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by MGWIZDAL on 2018-02-06.
 */
class DishResponseBody {
    @SerializedName("result")
    @Expose
    var result: List<DishResult>? = null
}
