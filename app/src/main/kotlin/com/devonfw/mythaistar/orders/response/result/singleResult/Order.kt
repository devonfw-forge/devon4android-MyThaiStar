package com.devonfw.mythaistar.orders.response.result.singleResult

import com.google.gson.annotations.SerializedName

/**
 * Created by MGWIZDAL on 2018-03-22.
 */
data class Order(
        @SerializedName("bookingId") val bookingId: Number,
        @SerializedName("bookingToken") val bookingToken: Any?,
        @SerializedName("hostId") val hostId: Any?,
        @SerializedName("id") val id: Number,
        @SerializedName("invitedGuestId") val invitedGuestId: Any?,
        @SerializedName("modificationCounter") val modificationCounter: Number,
        @SerializedName("revision") val revision: Any?
)