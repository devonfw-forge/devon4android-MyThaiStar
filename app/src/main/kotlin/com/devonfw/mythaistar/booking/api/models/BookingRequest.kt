package com.devonfw.mythaistar.booking.api.models

import com.google.gson.annotations.SerializedName

/**
 * Created by MGWIZDAL on 2018-03-13.
 */
data class BookingRequest(@SerializedName("booking") val booking : BookingRequestDetails) {
}