package com.yuiyeong.shop.domain.item

import com.yuiyeong.shop.domain.Category
import jakarta.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
open class Item(name: String, price: Int, stockQuantity: Int) {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    open var id: Long? = null
        protected set

    open var name: String = name
        protected set

    open var price: Int = price
        protected set

    open var stockQuantity: Int = stockQuantity
        protected set

    @ManyToMany(mappedBy = "items")
    open var categories: MutableList<Category> = arrayListOf()

    fun addStock(quantity: Int) {
        stockQuantity += quantity
    }

    fun reduceStockQuantity(quantity: Int) {
        val newStockQuantity = stockQuantity - quantity
        if (newStockQuantity < 0)
            throw IllegalStateException("quantity is greater than stockQuantity")
        stockQuantity = newStockQuantity
    }
}
