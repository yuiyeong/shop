package com.yuiyeong.shop.repository.query

import com.yuiyeong.shop.domain.Address
import com.yuiyeong.shop.domain.OrderStatus
import java.time.LocalDateTime

data class OrderQueryDto(
    val orderId: Long,
    val memberName: String,
    val orderDate: LocalDateTime,
    val orderStatus: OrderStatus,
    val deliveryAddress: Address
)
