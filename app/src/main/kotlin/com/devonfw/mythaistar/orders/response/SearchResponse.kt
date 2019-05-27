package com.devonfw.mythaistar.orders.response

import com.devonfw.mythaistar.orders.response.result.SearchResult
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by MGWIZDAL on 2018-03-22.
 */
class SearchResponse {
    @SerializedName("result")
    @Expose
    var result: List<SearchResult>? = null
}