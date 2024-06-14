package com.yuiyeong.shop.repository.query

import jakarta.persistence.EntityManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class OrderQueryRepository(@Autowired private val em: EntityManager) {
    fun findAll(): List<OrderQueryDto> {
        return em.createQuery(
            "select new com.yuiyeong.shop.repository.query.OrderQueryDto(o.id, m.name, o.orderDate, o.status, d.address)" +
                    " o from Order o" +
                    " join o.member m" +
                    " join o.delivery d",
            OrderQueryDto::class.java
        ).resultList
    }
}