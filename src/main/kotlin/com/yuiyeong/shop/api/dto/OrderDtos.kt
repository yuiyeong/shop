package com.yuiyeong.shop.api.dto

import com.yuiyeong.shop.domain.Address
import com.yuiyeong.shop.domain.OrderStatus
import java.time.LocalDateTime

data class SimpleOrderDto(
    val orderId: Long,
    val memberName: String,
    val orderDate: LocalDateTime,
    val orderStatus: OrderStatus,
    val deliveryAddress: Address
)