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
    open var categories: List<Category> = listOf(),

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    open var id: Long? = null
) {}
