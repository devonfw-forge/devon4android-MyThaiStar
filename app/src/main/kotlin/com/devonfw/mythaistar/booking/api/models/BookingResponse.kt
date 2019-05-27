package com.devonfw.mythaistar.booking.api.models

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
/**
 * Created by MGWIZDAL on 2018-03-13.
 */
data class BookingResponse(@SerializedName("assistants") @Expose val assistants: Int,
                           @SerializedName("bookingDate") @Expose val bookingDate: Long,
                           @SerializedName("bookingToken")  @Expose val bookingToken: String,
                           @SerializedName("bookingType") @Expose val bookingType: String,
                           @SerializedName("canceled") @Expose val canceled: Boolean,
                           @SerializedName("comment") @Expose val comment: String?,
                           @SerializedName("creationDate") @Expose val creationDate: Long,
                           @SerializedName("email") @Expose val email: String,
                           @SerializedName("expirationDate") @Expose val expirationDate: Long,
                           @SerializedName("id") @Expose val id: Int,
                           @SerializedName("modificationCounter") @Expose val modificationCounter: Int,
                           @SerializedName("name") @Expose val name: String,
                           @SerializedName("orderId") @Expose val orderId: Any?,
                           @SerializedName("revision") @Expose val revision: Any?,
                           @SerializedName("tableId") @Expose val tableId: Any?,
                           @SerializedName("userId") @Expose val userId: Any?) {
}