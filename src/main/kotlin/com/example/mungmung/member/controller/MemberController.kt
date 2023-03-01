package com.example.mungmung.member.controller

import com.example.mungmung.member.request.SignUpRequest
import com.example.mungmung.member.service.MemberService

import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@Slf4j
@RestController
@RequestMapping("/member")
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
class UserController {

    @Autowired
    val service: MemberService? = null

    @GetMapping("/naver-login")
    open fun authNaver(@RequestParam code: String?, @RequestParam state: String?): String? {
        println("authNaver()")

        return service!!.authenticationNaver(code, state)
    }

    @PostMapping("/sign-up")
    fun signUp(@RequestBody request: SignUpRequest): Boolean {
       println("signUp: $request")
        return service!!.signUp(request)
    }

    @GetMapping("/check-email/{email}")
    fun emailValidation(@PathVariable("email") email: String): Boolean {
        println("emailValidation(): $email")

        val result = service!!.emailValidation(email)
        println("email result: $result")

        return result
    }

    @GetMapping("/check-nickname/{nickname}")
    fun nicknameValidation(@PathVariable("nickname") nickname: String): Boolean {
        println("nicknameValidation(): $nickname")

        val result = service!!.nicknameValidation(nickname)
        println("nickname result: $result")

        return result
    }










}