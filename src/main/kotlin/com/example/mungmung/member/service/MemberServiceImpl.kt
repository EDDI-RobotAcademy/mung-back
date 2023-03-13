package com.example.mungmung.member.service

import com.example.mungmung.member.entity.Member
import com.example.mungmung.member.repository.MemberRepository
import com.example.mungmung.member.request.SignInRequest
import com.example.mungmung.member.request.SignInError
import com.example.mungmung.member.request.SignUpRequest
import com.example.mungmung.security.entity.Authentication
import com.example.mungmung.security.entity.BasicAuthentication
import com.example.mungmung.security.repository.AuthenticationRepository
import com.example.mungmung.security.service.RedisService
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate
import java.util.*

@Service
class MemberServiceImpl: MemberService  {
    @Value("\${naver-client-id}")
    lateinit var naverClientId : String

    @Value("\${naver-client-secret}")
    lateinit var naverClientSecret : String

    @Autowired
    val memberRepository: MemberRepository? = null

    @Autowired
    val authenticationRepository: AuthenticationRepository? = null

    @Autowired
    val redisService: RedisService? = null

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
        params.add("grant_type", "authorization_code")
        params.add("client_id", naverClientId) // 클라이언트 ID
        params.add("client_secret", naverClientSecret)// 클라이언트 시크릿~
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
    override fun signUp(signUpRequest: SignUpRequest): Boolean{
        val member: Member = signUpRequest.toMember()

        memberRepository!!.save(member)

        val auth = BasicAuthentication(member,
                Authentication.BASIC_AUTH, signUpRequest.password)

        authenticationRepository!!.save(auth)

        return true
    }

    override fun emailValidation(email: String): Boolean {
        val maybeMember: Optional<Member> = memberRepository!!.findByEmail(email)

        return !maybeMember.isPresent
    }

    override fun nicknameValidation(nickname: String): Boolean {
        val maybeMember: Optional<Member> = memberRepository!!.findByNickname(nickname)

        return !maybeMember.isPresent
    }

    override fun signIn(signInRequest: SignInRequest): String {
        val maybeMember: Optional<Member> = memberRepository!!.findByEmail(signInRequest.getEmail())

        return if(maybeMember.isPresent) {

            val member = maybeMember.get()

            if( member.isRightPassword(signInRequest.getPassword()) ) {

                val userToken = UUID.randomUUID()

                redisService!!.deleteByKey(userToken.toString())
                redisService!!.setKeyAndValue(userToken.toString(), member.getId()!!)

                userToken.toString()

            } else {
                SignInError.WRONG_PASSWORD.toString()
            }
        } else {
            SignInError.WRONG_EMAIL.toString()
        }
    }
}