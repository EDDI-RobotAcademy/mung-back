package com.example.mungmung.walkingDog.repository

import com.example.mungmung.walkingDog.entity.WalkingDog
import org.springframework.data.jpa.repository.JpaRepository

interface WalkingDogRepository : JpaRepository<WalkingDog, Long> {
}