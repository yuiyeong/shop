package com.yuiyeong.shop.repository

import com.yuiyeong.shop.domain.*
import com.yuiyeong.shop.domain.item.Book
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@SpringBootTest
@Transactional
class OrderRepositoryTest @Autowired constructor(
    private val orderRepository: OrderRepository,
    private val memberRepository: MemberRepository,
    private val itemRepository: ItemRepository
) {

    @Test
    fun testSaveAndFind() {
        //given
        val member = Member("m", Address("city","street","12345"))
        memberRepository.save(member)

        val item  = Book("author","IDND123","book", 1000, 10)
        itemRepository.save(item)

        val delivery = Delivery(member.address)
        val orderItem = OrderItem(item, item.price, 1)
        val order = Order(member, delivery, arrayListOf(orderItem))

        //when
        val savedId = orderRepository.save(order)

        //then
        val foundOne = orderRepository.findOrder(savedId)
        assertEquals(order, foundOne)
        assertEquals(member, foundOne.member)
        assertEquals(delivery, foundOne.delivery)
        assertEquals(1, foundOne.orderItems.count())
        assertEquals(orderItem, foundOne.orderItems[0])
    }
}