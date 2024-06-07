package com.yuiyeong.shop.repository

import com.yuiyeong.shop.domain.Address
import com.yuiyeong.shop.domain.Member
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Test
import kotlin.test.assertEquals

@SpringBootTest
@Transactional
class MemberRepositoryTest(@Autowired private val memberRepository: MemberRepository) {

    @Test
    fun testSavingMember() {
        //given
        val member = Member("tester", Address("test city", "test street", "test zipcode"))

        // when
        val savedId = memberRepository.save(member)

        // then
        val foundOne = memberRepository.findMember(savedId)
        assertEquals(foundOne, member)
    }
}