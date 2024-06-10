package com.yuiyeong.shop.domain

import com.yuiyeong.shop.domain.item.Item
import jakarta.persistence.*

@Entity
class OrderItem(item: Item, orderPrice: Int, count: Int) {
    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    var id: Long? = null
        private set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    var item: Item = item
        private set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    var order: Order? = null
        private set

    var oderPrice: Int = orderPrice
        private set

    var count: Int = count
        private set


    companion object {
        fun createOrderItem(item: Item, orderPrice: Int, count: Int): OrderItem {
            val orderItem = OrderItem(item, orderPrice, count)
            item.reduceStockQuantity(count)
            return orderItem
        }
    }
}
