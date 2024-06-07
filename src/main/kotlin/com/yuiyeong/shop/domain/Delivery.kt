package com.yuiyeong.shop.domain

import jakarta.persistence.*

@Entity
class Delivery protected constructor(
    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    var order: Order?,

    @Embedded
    var address: Address,

    @Enumerated(EnumType.STRING)
    var status: DeliveryStatus = DeliveryStatus.READY,

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    var id: Long? = null
) {
    companion object {
        fun createDelivery(address: Address): Delivery {
            return Delivery(null, address)
        }
    }
}
