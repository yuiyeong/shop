package com.yuiyeong.shop

import com.yuiyeong.shop.domain.*
import com.yuiyeong.shop.domain.item.Book
import com.yuiyeong.shop.domain.item.Item
import jakarta.annotation.PostConstruct
import jakarta.persistence.EntityManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class InitDb(@Autowired private val initService: InitService) {
    @PostConstruct
    fun seedData() {
        initService.dbInit1()
        initService.dbInit2()
    }
}

@Component
@Transactional
class InitService(@Autowired private val em: EntityManager) {
    fun dbInit1() {
        // 회원 1, 회원 1이 주문한 order
        val m1 = createMember("member1")
        em.persist(m1)

        val i1 = createItem("itemForM1", 1000, 100)
        em.persist(i1)

        val orderItem1 = OrderItem.createOrderItem(i1, i1.price, 10)
        val delivery = Delivery(m1.address)
        val order = Order(m1, delivery, arrayListOf(orderItem1))
        em.persist(order)
    }

    fun dbInit2() {
        // 회원 2, 회원 2가 주문한 order
        val m2 = createMember("member2")
        em.persist(m2)

        val i1 = createItem("item1ForM2", 1000, 100)
        em.persist(i1)

        val i2 = createItem("item2ForM2", 2000, 20)
        em.persist(i2)

        val orderItem1 = OrderItem.createOrderItem(i1, i1.price, 10)
        val orderItem2 = OrderItem.createOrderItem(i2, i2.price, 5)
        val delivery = Delivery(m2.address)
        val order = Order(m2, delivery, arrayListOf(orderItem1, orderItem2))
        em.persist(order)
    }

    private fun createMember(name: String): Member {
        return Member(name, Address("$name City", "$name Street", "$name Zipcode"))
    }

    private fun createItem(name: String, price: Int, stockQuantity: Int): Item {
        return Book("$name author", "$name isbn", name, price, stockQuantity)
    }
}