package com.yuiyeong.shop.service

import com.yuiyeong.shop.domain.Address
import com.yuiyeong.shop.domain.DeliveryStatus
import com.yuiyeong.shop.domain.Member
import com.yuiyeong.shop.domain.OrderStatus
import com.yuiyeong.shop.domain.item.Book
import com.yuiyeong.shop.repository.ItemRepository
import com.yuiyeong.shop.repository.MemberRepository
import com.yuiyeong.shop.repository.OrderRepository
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.assertEquals

@SpringBootTest
@Transactional
class OrderServiceTest @Autowired constructor(
    private val orderService: OrderService,
    private val orderRepository: OrderRepository,
    private val memberRepository: MemberRepository,
    private val itemRepository: ItemRepository
) {

    @Test
    fun order() {
        // given
        val member = Member("tester", Address("test city", "test street", "test zipcode"))
        val savedMemberId = memberRepository.save(member)

        val book = Book("test author", "test isbn", "test book name", 1000, 100)
        val savedBookId = itemRepository.save(book)

        // when
        val quantity = 3
        val savedId = orderService.order(savedMemberId, savedBookId, 3)

        // then
        val order = orderRepository.findOrder(savedId)
        assertEquals(1, order.orderItems.count())
        assertEquals(quantity, order.orderItems[0].count)
        assertEquals(member.address, order.delivery.address)
        assertEquals(member, order.member)
        assertEquals(OrderStatus.ORDER, order.status)
        assertEquals(DeliveryStatus.READY, order.delivery.status)
    }
}