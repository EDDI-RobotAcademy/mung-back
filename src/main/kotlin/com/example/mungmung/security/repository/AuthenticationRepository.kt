package com.example.mungmung.security.repository

import com.example.mungmung.security.entity.Authentication
import org.springframework.data.jpa.repository.JpaRepository

interface AuthenticationRepository : JpaRepository<Authentication, Long>{

}