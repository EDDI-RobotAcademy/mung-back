package com.example.mungmung.member.service

import org.springframework.web.bind.annotation.RequestParam

interface MemberService {

    fun authenticationNaver(code: String?, state: String?):String?

}