package com.example.mungmung.walkingDog.service

import com.example.mungmung.walkingDog.request.WalkingDogRegisterRequest
import org.springframework.web.multipart.MultipartFile

interface WalkingDogService {

    fun register(images: List<MultipartFile>,request: WalkingDogRegisterRequest):Boolean
}