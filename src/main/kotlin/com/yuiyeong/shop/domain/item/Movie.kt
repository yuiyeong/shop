package com.yuiyeong.shop.domain.item

import com.yuiyeong.shop.domain.Category
import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue("M")
class Movie(
    var director: String,
    var actor: String,
    name: String,
    price: Int,
    stockQuantity: Int,
    categories: List<Category> = listOf(),
    id: Long? = null
) : Item(name, price, stockQuantity, categories, id) {}
