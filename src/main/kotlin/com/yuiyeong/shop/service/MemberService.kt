package com.yuiyeong.shop.service

import com.yuiyeong.shop.domain.Address
import com.yuiyeong.shop.domain.Member
import com.yuiyeong.shop.repository.MemberRepository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberService(@Autowired private val memberRepository: MemberRepository) {

    @Transactional
    fun join(name: String, address: Address): Long {
        val member = Member(name, address)
        return memberRepository.save(member)
    }

    fun findMember(id: Long): Member {
        return memberRepository.findMember(id)
    }

    fun findAll(): List<Member> {
        return memberRepository.findAll()
    }

    @Transactional
    fun updateMemberName(id: Long, name: String): Long {
        val member = memberRepository.findMember(id)
        member.changeName(name)
        return member.id!!
    }
}