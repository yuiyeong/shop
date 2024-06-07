package com.yuiyeong.shop.domain

import jakarta.persistence.Embeddable

@Embeddable
data class Address(
    private val city: String,
    private val street: String,
    private val zipcode: String
)
