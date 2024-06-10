package com.yuiyeong.shop.domain.item

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue("A")
class Album(
    name: String,
    price: Int,
    stockQuantity: Int,
    artist: String,
    etc: String
) : Item(name, price, stockQuantity) {
    var artist: String = artist
        private set

    var etc: String = etc
        private set
}
