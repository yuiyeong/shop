package com.yuiyeong.shop.domain.item

import com.yuiyeong.shop.domain.Category
import jakarta.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
open class Item(
    open var name: String,
    open var price: Int,
    open var stockQuantity: Int,

    @ManyToMany(mappedBy = "items")
    open var categories: MutableList<Category> = arrayListOf(),

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    open var id: Long? = null
) {
    fun reduceStockQuantity(quantity: Int) {
        val newStockQuantity = stockQuantity - quantity
        if (newStockQuantity < 0)
            throw IllegalStateException("quantity is greater than stockQuantity")
        stockQuantity = newStockQuantity
    }
}
