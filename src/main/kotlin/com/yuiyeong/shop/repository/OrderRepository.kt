package com.yuiyeong.shop.repository

import com.yuiyeong.shop.domain.Member
import com.yuiyeong.shop.domain.Order
import jakarta.persistence.EntityManager
import jakarta.persistence.criteria.JoinType
import jakarta.persistence.criteria.Predicate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@Repository
class OrderRepository(@Autowired private val em: EntityManager) {
    fun save(order: Order): Long {
        em.persist(order)
        return order.id!!
    }

    fun findOrder(id: Long): Order {
        return em.find(Order::class.java, id)
    }

    fun findAllByStatusAndMemberName(orderSearch: OrderSearch): MutableList<Order> {
        val cb = em.criteriaBuilder
        val cq = cb.createQuery(Order::class.java)
        val orderRoot = cq.from(Order::class.java)
        val memberJoin = orderRoot.join<Order, Member>("member", JoinType.INNER)
        val predicates = arrayListOf<Predicate>()

        // 주문 상태에 대한 필터링
        orderSearch.orderStatus?.let {
            val statusPredicate = cb.equal(orderRoot.get<JvmType.Object>("status"), it)
            predicates.add(statusPredicate)
        }

        // 회원 이름으로 필터링
        orderSearch.memberName?.let {
            val namePredicate = cb.like(memberJoin.get("name"), it)
            predicates.add(namePredicate)
        }

        cq.where(cb.and(*predicates.toTypedArray<Predicate>()))
        val query = em.createQuery(cq).setMaxResults(1000) //최대 1000 건
        return query.resultList
    }

    fun findAllWithMemberDelivery() : List<Order> {
        return em.createQuery("select o from Order o" +
                " join fetch o.member m" +
                " join fetch o.delivery d", Order::class.java).resultList
    }
}