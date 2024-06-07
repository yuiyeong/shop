package com.yuiyeong.shop.domain.item

import com.yuiyeong.shop.domain.Category
import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue("B")
class Book(
    var author: String,
    var isbn: String,
    name: String,
    price: Int,
    stockQuantity: Int,
    categories: MutableList<Category> = arrayListOf(),
    id: Long? = null
) : Item(name, price, stockQuantity, categories, id) {}
