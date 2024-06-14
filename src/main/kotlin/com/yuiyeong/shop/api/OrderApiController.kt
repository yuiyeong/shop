package com.yuiyeong.shop.api

import com.yuiyeong.shop.api.dto.ListResult
import com.yuiyeong.shop.api.dto.OrderDto
import com.yuiyeong.shop.service.OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderApiController(@Autowired private val orderService: OrderService) {

    @GetMapping("/api/v2/orders")
    fun findOrders(): ListResult<OrderDto> {
        val list = orderService.findAll().map { OrderDto.create(it) }
        return ListResult(list)
    }

    @GetMapping("/api/v3/orders")
    fun findOrdersV3(): ListResult<OrderDto> {
        val list = orderService.findAllWithItems().map { OrderDto.create(it) }
        return ListResult(list)
    }

    @GetMapping("/api/v3.1/orders")
    fun findOrdersWithPaging(
        @RequestParam(value = "offset", defaultValue = "0") offset: Int,
        @RequestParam(value = "limit", defaultValue = "100") limit: Int
    ): ListResult<OrderDto> {
        // *ToOne 관계인 property 들만 fetch join 을 하고, collection 은 lazy loading 을 하게 한다.
        // 하지만 hibernate 의 default_batch_fetch_size 를 100 으로 설정해줌으로써 성능을 최적화할 수 있다.
        // 이렇게 했을 때만 paging 가능.
        val list = orderService.findAllWithMemberDelivery(offset, limit).map { OrderDto.create(it) }
        return ListResult(list)
    }
}