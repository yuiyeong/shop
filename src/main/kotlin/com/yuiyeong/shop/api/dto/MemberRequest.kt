package com.yuiyeong.shop.api.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

data class CreateMemberRequest(
    @NotEmpty
    val name: String,
    @NotNull
    val city: String,
    @NotNull
    val street: String,
    @NotNull
    val zipcode: String)

data class UpdateMemberRequest(@NotEmpty val name: String)
