package com.yuiyeong.shop.api.dto

import com.yuiyeong.shop.domain.Address
import com.yuiyeong.shop.domain.Order
import com.yuiyeong.shop.domain.OrderItem
import com.yuiyeong.shop.domain.OrderStatus
import java.time.LocalDateTime

data class SimpleOrderDto(
    val orderId: Long,
    val memberName: String,
    val orderDate: LocalDateTime,
    val orderStatus: OrderStatus,
    val deliveryAddress: Address
)

data class OrderDto(
    val orderId: Long,
    val memberName: String,
    val orderItems: List<OrderItemDto>,
    val orderDate: LocalDateTime,
    val orderStatus: OrderStatus,
    val deliveryAddress: Address
) {
    companion object {
        fun create(order: Order): OrderDto {
            val orderItems = order.orderItems.map { oi -> OrderItemDto.create(oi) }
            return OrderDto(
                order.id!!,
                order.member.name,
                orderItems,
                order.orderDate,
                order.status,
                order.delivery.address
            )
        }
    }
}

data class OrderItemDto(
    val itemId: Long,
    val itemName: String,
    val itemPrice: Int,
    val count: Int
) {
    companion object {
        fun create(orderItem: OrderItem): OrderItemDto {
            val item = orderItem.item
            return OrderItemDto(item.id!!, item.name, item.price, orderItem.count)
        }
    }
}
