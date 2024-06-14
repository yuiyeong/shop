package com.yuiyeong.shop.api

import com.yuiyeong.shop.api.dto.ListResult
import com.yuiyeong.shop.api.dto.OrderDto
import com.yuiyeong.shop.service.OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderApiController(@Autowired private val orderService: OrderService) {

    @GetMapping("/api/v2/orders")
    fun findOrders(): ListResult<OrderDto> {
        val list = orderService.findAll().map { OrderDto.create(it) }
        return ListResult(list)
    }
}