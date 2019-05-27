package com.devonfw.mythaistar.orders.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by MGWIZDAL on 2018-03-22.
 */
class SearchRequestBodyPagination(@SerializedName("size") @Expose val size : Int = 8,
                               @SerializedName("page")@Expose val page : Int = 1,
                               @SerializedName("total") @Expose val total : Int = 1){
}