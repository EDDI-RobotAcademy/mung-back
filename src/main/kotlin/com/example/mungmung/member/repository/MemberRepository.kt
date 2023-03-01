package com.example.mungmung.member.repository

import com.example.mungmung.member.entity.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface MemberRepository : JpaRepository<Member,Long> {
    @Query("select m from Member m where m.email = :email")
    fun findByEmail(email: String): Optional<Member>

    @Query("select m from Member m where m.nickname = :nickname")
    fun findByNickname(nickname: String): Optional<Member>

}