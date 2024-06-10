package com.yuiyeong.shop.domain.item

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue("M")
class Movie(
    director: String,
    actor: String,
    name: String,
    price: Int,
    stockQuantity: Int
) : Item(name, price, stockQuantity) {
    var director: String = director
        private set

    var actor: String = actor
        private set
}
