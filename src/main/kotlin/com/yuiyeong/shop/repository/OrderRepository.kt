package com.yuiyeong.shop.repository

import com.yuiyeong.shop.domain.Order
import jakarta.persistence.EntityManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class OrderRepository(@Autowired private val em: EntityManager) {
    fun save(order: Order): Long {
        em.persist(order)
        return order.id!!
    }

    fun findOrder(id: Long): Order {
        return em.find(Order::class.java, id)
    }
}