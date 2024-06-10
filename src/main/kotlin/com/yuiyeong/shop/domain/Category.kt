package com.yuiyeong.shop.domain

import com.yuiyeong.shop.domain.item.Item
import jakarta.persistence.*

@Entity
class Category(name: String) {
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    var id: Long? = null
        private set

    var name: String = name
        private set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    var parent: Category? = null
        private set

    @ManyToMany
    @JoinTable(
        name = "category_item",
        joinColumns = [JoinColumn(name = "category_id")],
        inverseJoinColumns = [JoinColumn(name = "item_id")]
    )
    var items: MutableList<Item> = arrayListOf()
        private set

    @OneToMany(mappedBy = "parent")
    var child: MutableList<Category> = arrayListOf()
        private set
}
