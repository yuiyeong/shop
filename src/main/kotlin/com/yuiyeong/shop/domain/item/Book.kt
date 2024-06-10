package com.yuiyeong.shop.domain.item

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue("B")
class Book(
    author: String,
    isbn: String,
    name: String,
    price: Int,
    stockQuantity: Int
) : Item(name, price, stockQuantity) {
    var author: String = author
        private set

    var isbn: String = isbn
        private set
}
