package com.yuiyeong.shop.repository

import com.yuiyeong.shop.domain.OrderStatus

data class OrderSearch(val memberName: String?, val orderStatus: OrderStatus?)
