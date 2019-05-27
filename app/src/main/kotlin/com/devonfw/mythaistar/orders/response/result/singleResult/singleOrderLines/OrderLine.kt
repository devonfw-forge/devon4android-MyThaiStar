package com.devonfw.mythaistar.orders.response.result.singleResult.singleOrderLines

/**
 * Created by MGWIZDAL on 2018-03-22.
 */
data class OrderLine(
        val amount: Number?,
        val comment: String?,
        val description: String?,
        val dishId: Number?,
        val id: Number,
        val imageId: Number?,
        val modificationCounter: Number,
        val name: String?,
        val orderId: Number?,
        val price: Number?,
        val revision: Any?
)
