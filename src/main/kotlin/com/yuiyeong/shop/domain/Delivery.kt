package com.yuiyeong.shop.domain

import jakarta.persistence.*

@Entity
class Delivery(
    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    val order: Order,

    @Embedded
    var address: Address,

    @Enumerated(EnumType.STRING)
    var status: DeliveryStatus = DeliveryStatus.READY,

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    val id: Long? = null
) {}
