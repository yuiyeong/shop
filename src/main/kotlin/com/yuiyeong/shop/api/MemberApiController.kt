package com.yuiyeong.shop.api

import com.yuiyeong.shop.api.dto.*
import com.yuiyeong.shop.domain.Address
import com.yuiyeong.shop.service.MemberService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
class MemberApiController(private val memberService: MemberService) {

    @GetMapping("/api/v2/members")
    fun getMembers(): ListResult<MemberDto> {
        val list = memberService.findAll().map { MemberDto(it.id!!, it.name, it.address) }
        return ListResult(list)
    }

    @PostMapping("/api/v2/members")
    fun join(@Valid @RequestBody req: CreateMemberRequest): CreateMemberResponse {
        val joinedId = memberService.join(req.name, Address(req.city, req.street, req.zipcode))
        return CreateMemberResponse(joinedId)
    }

    @PutMapping("/api/v2/members/{id}")
    fun updateName(
        @PathVariable("id") id: Long,
        @Valid @RequestBody req: UpdateMemberRequest
    ): UpdateMemberResponse {
        val memberId = memberService.updateMemberName(id, req.name)
        return UpdateMemberResponse(memberId)
    }
}