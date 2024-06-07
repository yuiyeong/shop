package com.yuiyeong.shop.repository

import com.yuiyeong.shop.domain.item.Item
import jakarta.persistence.EntityManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class ItemRepository(@Autowired private val em: EntityManager) {
    fun save(item: Item): Long {
        em.persist(item)
        return item.id!!
    }

    fun findItem(itemId: Long): Item {
        return em.find(Item::class.java, itemId)
    }

    fun findAll(): List<Item> {
        return em.createQuery("select i from Item i", Item::class.java).resultList
    }
}