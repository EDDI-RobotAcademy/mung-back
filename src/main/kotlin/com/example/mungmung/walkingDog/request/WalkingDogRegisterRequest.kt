package com.example.mungmung.walkingDog.request

import lombok.Getter
import lombok.RequiredArgsConstructor
import lombok.ToString

@Getter
@ToString
@RequiredArgsConstructor
class WalkingDogRegisterRequest {

    var memberId: Long? = null
    var indexOfThumbnail: Int? = null
    var imageList: List<String>? = null
    var title: String? = null
    var content: String? = null
    var writer: String? = null
}