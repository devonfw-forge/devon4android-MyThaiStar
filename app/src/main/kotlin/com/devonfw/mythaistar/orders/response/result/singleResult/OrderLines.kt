package com.devonfw.mythaistar.orders.response.result.singleResult

import com.devonfw.mythaistar.orders.response.result.singleResult.singleOrderLines.Extras
import com.devonfw.mythaistar.orders.response.result.singleResult.singleOrderLines.OrderLine

data class OrderLines(
        val dish: OrderLine,
        val extras: Array<Extras>,
        val order: Any?,
        val orderLine: OrderLine
){}

