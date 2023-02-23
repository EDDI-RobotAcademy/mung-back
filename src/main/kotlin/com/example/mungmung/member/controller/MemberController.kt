package com.example.mungmung.member.controller

import com.example.mungmung.member.service.MemberService

import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate


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






}