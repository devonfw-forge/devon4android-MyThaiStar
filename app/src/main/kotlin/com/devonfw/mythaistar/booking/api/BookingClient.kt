package com.devonfw.mythaistar.booking.api

import com.devonfw.mythaistar.booking.api.models.BookingRequest
import com.devonfw.mythaistar.booking.api.models.BookingResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * Created by MGWIZDAL on 2018-03-13.
 */
interface BookingClient {
    @Headers("Content-type: application/json")
    @POST("/mythaistar/services/rest/bookingmanagement/v1/booking")
    fun postBooking(@Body booking: BookingRequest): Call<BookingResponse>
}