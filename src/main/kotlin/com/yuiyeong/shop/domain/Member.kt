package com.yuiyeong.shop.domain

import jakarta.persistence.*

@Entity
class Member(
    var name: String,
    @Embedded var address: Address,
    @OneToMany(mappedBy = "member") var orders: List<Order> = listOf(),

    @Id @GeneratedValue
    @Column(name = "member_id") var id: Long? = null
) {}
