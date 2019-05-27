package com.devonfw.mythaistar.orders.request

import com.google.gson.annotations.SerializedName
/**
 * Created by MGWIZDAL on 2018-03-22.
 */
data class SearchRequestBody(@SerializedName("pagination") val pagination : SearchRequestBodyPagination,
                             @SerializedName("sort") val sort : Array<String> = emptyArray<String>()) {
}