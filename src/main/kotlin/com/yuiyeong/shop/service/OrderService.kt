package com.yuiyeong.shop.service

import com.yuiyeong.shop.domain.Delivery
import com.yuiyeong.shop.domain.Order
import com.yuiyeong.shop.domain.OrderItem
import com.yuiyeong.shop.repository.ItemRepository
import com.yuiyeong.shop.repository.MemberRepository
import com.yuiyeong.shop.repository.OrderRepository
import com.yuiyeong.shop.repository.OrderSearch
import com.yuiyeong.shop.repository.query.OrderQueryDto
import com.yuiyeong.shop.repository.query.OrderQueryRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrderService @Autowired constructor(
    private val orderRepository: OrderRepository,
    private val orderQueryRepository: OrderQueryRepository,
    private val itemRepository: ItemRepository,
    private val memberRepository: MemberRepository,
) {
    @Transactional
    fun order(memberId: Long, itemId: Long, count: Int): Long {
        val member = memberRepository.findMember(memberId)

        val item = itemRepository.findItem(itemId)
        val orderItem = OrderItem.createOrderItem(item, item.price, count)

        val delivery = Delivery.createDelivery(member.address)
        val order = Order(member, delivery, mutableListOf(orderItem))
        orderRepository.save(order)
        return order.id!!
    }

    fun findAll(): List<Order> {
        return orderRepository.findAllByStatusAndMemberName(OrderSearch(null, null))
    }

    fun findAllWithMemberDelivery(): List<Order> {
        return orderRepository.findAllWithMemberDelivery()
    }

    fun findAllByQueryDto(): List<OrderQueryDto> {
        return orderQueryRepository.findAll()
    }
}