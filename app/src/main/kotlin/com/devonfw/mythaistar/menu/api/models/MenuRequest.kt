package com.devonfw.mythaistar.menu.api.models

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by MGWIZDAL on 2018-02-06.
 */

data class MenuRequest(val sort: Array<String> = emptyArray<String>(),
                       val categories: Array<String> = emptyArray<String>(),
                       @SerializedName("searchBy") val searchBy: String = "",
                       @SerializedName("maxPrice") val maxPrice: Int = 100,
                       @SerializedName("minLikes") val minLikes: Int = 0,
                       @SerializedName("showOrder") val showOrder: Int = 0) {

}