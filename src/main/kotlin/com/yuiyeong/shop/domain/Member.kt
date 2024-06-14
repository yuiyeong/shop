package com.yuiyeong.shop.domain

import jakarta.persistence.*

@Entity
class Member(name: String, address: Address) {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    var id: Long? = null
        private set

    var name: String = name
        private set

    @Embedded
    var address: Address = address
        private set

    @OneToMany(mappedBy = "member")
    var orders: MutableList<Order> = arrayListOf()
        private set

    fun changeName(name: String) {
        this.name = name
    }
}
