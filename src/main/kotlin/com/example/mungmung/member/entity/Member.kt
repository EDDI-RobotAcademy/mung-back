package com.example.mungmung.member.entity

import jakarta.persistence.*
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

@Entity
@NoArgsConstructor
@Setter
@Getter
class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null

    @Column(nullable = false)
    private val email: String? = null

    @Column(nullable = false)
    private val username: String? = null

    @Column(nullable = false)
    private val birthdate = 0

    @Column(nullable = false)
    private val socialType:SocialType? = null

}