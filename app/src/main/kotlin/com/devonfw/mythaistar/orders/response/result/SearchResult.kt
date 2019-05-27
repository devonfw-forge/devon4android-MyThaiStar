package com.devonfw.mythaistar.orders.response.result

import com.devonfw.mythaistar.booking.api.models.BookingResponse
import com.devonfw.mythaistar.orders.response.result.singleResult.Order
import com.devonfw.mythaistar.orders.response.result.singleResult.OrderLines
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by MGWIZDAL on 2018-03-22.
 */
class SearchResult {
    @SerializedName("order")
    @Expose
    var order: Order? = null
    @SerializedName("booking")
    @Expose
    var booking: BookingResponse? = null
    @SerializedName("invitedGuest")
    @Expose
    var invitedGuest: Any? = null
    @SerializedName("orderLines")
    @Expose
    var orderLines: OrderLines? = null
    @SerializedName("host")
    @Expose
    var host: Any? = null
}