package com.example.mungmung.member.entity

import lombok.Getter

@Getter
enum class SocialType(val type: String) {

    NAVER("naver"),
    KAKAO("kakao"),
    LOCAL("local")
}