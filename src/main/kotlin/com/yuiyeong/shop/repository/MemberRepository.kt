package com.yuiyeong.shop.repository

import com.yuiyeong.shop.domain.Member
import jakarta.persistence.EntityManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class MemberRepository(@Autowired val em: EntityManager) {

    fun save(member: Member): Long {
        em.persist(member)
        return member.id!!
    }

    fun findMember(id: Long): Member {
        return em.find(Member::class.java, id)
    }

    fun findAll(): List<Member> {
        return em.createQuery("select m from Member m", Member::class.java).resultList
    }

    fun findByName(name: String): List<Member> {
        return em.createQuery(
            "select m from Member m where m.name = :name",
            Member::class.java
        ).setParameter("name", name).resultList
    }
}