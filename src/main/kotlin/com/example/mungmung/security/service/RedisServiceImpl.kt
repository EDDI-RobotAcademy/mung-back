package com.example.mungmung.security.service

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service
import java.time.Duration

@Service
class RedisServiceImpl: RedisService {

    private val redisTemplate: StringRedisTemplate? = null

    override fun setKeyAndValue(token: String, memNo: Long) {
        val memNoToString = memNo.toString()
        val value = redisTemplate?.opsForValue()
        value?.set(token, memNoToString, Duration.ofHours(2))
    }
    override fun getValueByKey(token: String): Long {
        val value = redisTemplate?.opsForValue()
        val tempMemNo = value?.get(token)
        val memNo = tempMemNo!!.toLong()
        return memNo
    }

    override fun deleteByKey(token: String) {
        redisTemplate?.delete(token)
    }

    override fun isRefreshTokenExists(token: String): Boolean {
        return getValueByKey(token) != null
    }

}