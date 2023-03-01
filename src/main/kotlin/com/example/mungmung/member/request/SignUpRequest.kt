package com.example.mungmung.member.request

import com.example.mungmung.member.entity.Member
import lombok.Getter
import lombok.ToString

@ToString
@Getter
class SignUpRequest (
        val email: String?,
        val password: String?,
        val nickname: String?,
        val memberType: String?
        ){

        fun toMember(): Member {
                return Member(email, nickname, memberType)
        }
}