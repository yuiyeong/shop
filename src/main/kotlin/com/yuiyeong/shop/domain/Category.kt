package com.yuiyeong.shop.domain

import com.yuiyeong.shop.domain.item.Item
import jakarta.persistence.*

@Entity
class Category(
    var name: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    var parent: Category? = null,

    @ManyToMany
    @JoinTable(
        name = "category_item",
        joinColumns = [JoinColumn(name = "category_id")],
        inverseJoinColumns = [JoinColumn(name = "item_id")]
    )
    val items: List<Item> = ArrayList(),

    @OneToMany(mappedBy = "parent")
    var child: List<Category> = listOf(),

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    var id: Long? = null
) {}
