package com.example.mungmung.member.service

import com.example.mungmung.member.repository.MemberRepository
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate

@Slf4j
@Service
class MemberServiceImpl: MemberService  {

    @Autowired
    val memberRepository: MemberRepository? = null

    override fun authenticationNaver(code: String?, state: String?): String?{
        val parser = JsonParser()
        val accessElement: JsonElement = parser.parse(requestAccessToken(generateAuthCodeRequest(code!!, state!!)!!)!!.body)

        val accessToken: String = accessElement.asJsonObject.get("access_token").asString
        val userInfo : String? = requestProfile(generateProfileRequest(accessToken)!!)!!.body

        val userInfoToJson : JsonElement = parser.parse(userInfo)
        System.out.println(userInfoToJson.asJsonObject.get("response").asJsonObject.get("id").asString)
        System.out.println(userInfoToJson.asJsonObject.get("response").asJsonObject.get("email").asString)
        System.out.println(userInfoToJson.asJsonObject.get("response").asJsonObject.get("name").asString)

        return userInfo
    }

    private fun generateAuthCodeRequest(code: String, state: String): HttpEntity<MultiValueMap<String, String>>? {
        val headers = org.springframework.http.HttpHeaders()
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
        val params: MultiValueMap<String, String> = LinkedMultiValueMap()
        params.add("grant_type", "")
        params.add("client_id", "") // 클라이언트 ID
        params.add("client_secret", "yvJMoXP8eC")// 클라이언트 시크릿~
        params.add("code", code)
        params.add("state", state)
        return HttpEntity(params, headers)
    }

    private fun requestAccessToken(request: HttpEntity<*>): ResponseEntity<String?>? {
        val restTemplate = RestTemplate()
        return restTemplate.exchange(
            "https://nid.naver.com/oauth2.0/token",
            HttpMethod.POST,
            request,
            String::class.java
        )
    }

    private fun requestProfile(request: HttpEntity<*>): ResponseEntity<String?>? {
        val restTemplate = RestTemplate()
        return restTemplate.exchange(
            "https://openapi.naver.com/v1/nid/me",
            HttpMethod.POST,
            request,
            String::class.java
        )
    }
    private fun generateProfileRequest(accessToken: String): HttpEntity<MultiValueMap<String?, String?>?>? {
        val headers = HttpHeaders()
        headers.add("Authorization", "Bearer $accessToken")
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
        return HttpEntity(headers)
    }


}