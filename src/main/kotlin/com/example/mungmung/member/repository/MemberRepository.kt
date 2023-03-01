package com.example.mungmung.member.repository

import com.example.mungmung.member.entity.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface MemberRepository : JpaRepository<Member,Long> {
    @Query("select m from Member m where m.email = :email")
    fun findByEmail(@Param("email") email: String): Optional<Member>

}