package com.devonfw.mythaistar.orders.response.result.singleResult.singleOrderLines

/**
 * Created by MGWIZDAL on 2018-03-22.
 */
data class Extras(
        val description: String,
        val id: Number,
        val modificationCounter: Number,
        val name: String,
        val price: Number,
        val revision: Any?
)