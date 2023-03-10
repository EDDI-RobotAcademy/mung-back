package com.example.mungmung.security.service

interface RedisService {
    fun setKeyAndValue(token: String, memNo: Long)
    fun getValueByKey(token: String): Long
    fun deleteByKey(token: String)
    fun isRefreshTokenExists(token: String): Boolean
}