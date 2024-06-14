package com.yuiyeong.shop.api.dto

import com.yuiyeong.shop.domain.Address

data class MemberDto(
    val id: Long,
    val name: String,
    val address: Address)
