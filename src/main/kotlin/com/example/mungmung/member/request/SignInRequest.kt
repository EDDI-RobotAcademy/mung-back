package com.example.mungmung.member.request

data class SignInRequest(
        private val email: String,
        private val password: String) {

    fun getEmail(): String {
        return email
    }

    fun getPassword(): String {
        return password
    }
}
