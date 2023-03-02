package com.example.mungmung.walkingDog.entity

import com.example.mungmung.utility.StringListConverter
import jakarta.persistence.Column
import jakarta.persistence.Convert
import lombok.NoArgsConstructor
import lombok.Setter

@Setter
@NoArgsConstructor
class WalkingDogInfo {

    @Column(nullable = false)
    private var thumbnailPath : String? = null

    @Convert(converter = StringListConverter::class)
    var imagesName: List<String>? = null

    constructor(thumbnailPath: String?, productImagesName: List<String>?) {
        this.thumbnailPath = thumbnailPath
        this.imagesName = productImagesName
    }
}