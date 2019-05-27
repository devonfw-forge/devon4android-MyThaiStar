package com.devonfw.mythaistar.orders

import com.devonfw.mythaistar.orders.request.SearchRequestBody
import com.devonfw.mythaistar.orders.response.SearchResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * Created by MGWIZDAL on 2018-03-22.
 */
interface OrderSearchClient {
    @Headers("Content-type: application/json")
    @POST("/mythaistar/services/rest/ordermanagement/v1/order/search")
    fun postAuthSearch( @Body searchRequestBody: SearchRequestBody): Call<SearchResponse>
}