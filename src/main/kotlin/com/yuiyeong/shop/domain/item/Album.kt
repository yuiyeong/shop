package com.yuiyeong.shop.domain.item

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue("A")
class Album(
    artist: String,
    etc: String,
    name: String,
    price: Int,
    stockQuantity: Int
) : Item(name, price, stockQuantity) {
    var artist: String = artist
        private set

    var etc: String = etc
        private set
}
