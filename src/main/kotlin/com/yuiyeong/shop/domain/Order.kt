package com.yuiyeong.shop.domain

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
class Order(member: Member, delivery: Delivery, orderItems: MutableList<OrderItem>) {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    var id: Long? = null
        private set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    var member: Member = member
        private set

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL])
    var orderItems: MutableList<OrderItem> = orderItems
        private set

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var delivery: Delivery = delivery
        private set

    var orderDate: LocalDateTime = LocalDateTime.now()
        private set

    @Enumerated(EnumType.STRING)
    var status: OrderStatus = OrderStatus.ORDER
        private set

    init {
        member.orders.add(this)
        delivery.determineOrder(this)
        orderItems.forEach { it.determineOrder(this) }
    }
}
