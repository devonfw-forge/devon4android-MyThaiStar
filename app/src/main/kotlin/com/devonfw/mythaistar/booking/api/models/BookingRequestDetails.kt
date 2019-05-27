package com.devonfw.mythaistar.booking.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by MGWIZDAL on 2018-03-13.
 */
class BookingRequestDetails(@SerializedName("bookingDate") @Expose val bookingDate : String,
                            @SerializedName("name")@Expose val name : String,
                            @SerializedName("email") @Expose val email : String,
                            @SerializedName("assistants") @Expose val assistants : Int,
                            @SerializedName("bookingType") @Expose val bookingType : Int = 0) {
}