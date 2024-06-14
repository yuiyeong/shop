package com.yuiyeong.shop.domain

import jakarta.persistence.*

@Entity
class Delivery(address: Address) {
    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    var id: Long? = null
        private set

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    var order: Order? = null
        private set

    @Embedded
    var address: Address = address
        private set

    @Enumerated(EnumType.STRING)
    var status: DeliveryStatus = DeliveryStatus.READY
        private set

    fun determineOrder(order: Order) {
        this.order = order
    }

    companion object {
        fun createDelivery(address: Address): Delivery {
            return Delivery(address)
        }
    }
}
