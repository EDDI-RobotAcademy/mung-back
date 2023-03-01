package com.example.mungmung.member.service

import com.example.mungmung.member.request.SignUpRequest

interface MemberService {

    fun authenticationNaver(code: String?, state: String?):String?

    fun signUp(signUpRequest: SignUpRequest): Boolean

    fun emailValidation(email: String): Boolean

    fun nicknameValidation(nickname: String): Boolean

}