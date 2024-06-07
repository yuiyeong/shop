package com.yuiyeong.shop.domain

import com.yuiyeong.shop.domain.item.Item
import jakarta.persistence.*

@Entity
class OrderItem(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    var item: Item,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    var order: Order,

    var oderPrice: Int,
    var count: Int,

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    var id: Long? = null
) {}
