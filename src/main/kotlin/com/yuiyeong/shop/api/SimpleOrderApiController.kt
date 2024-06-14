package com.yuiyeong.shop.api

import com.yuiyeong.shop.api.dto.ListResult
import com.yuiyeong.shop.api.dto.SimpleOrderDto
import com.yuiyeong.shop.service.OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SimpleOrderApiController(@Autowired private val orderService: OrderService) {

    @GetMapping("/api/v2/simple-orders")
    fun getOrders(): ListResult<SimpleOrderDto> {
        val list = orderService.findAll()
            .map { SimpleOrderDto(it.id!!, it.member.name, it.orderDate, it.status, it.delivery.address) }
        return ListResult(list)
    }

    @GetMapping("/api/v3/simple-orders")
    fun getOrdersV3(): ListResult<SimpleOrderDto> {
        val list = orderService.findAllWithMemberDelivery()
            .map { SimpleOrderDto(it.id!!, it.member.name, it.orderDate, it.status, it.delivery.address) }
        return ListResult(list)
    }
}