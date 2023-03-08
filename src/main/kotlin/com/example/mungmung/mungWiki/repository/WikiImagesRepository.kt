package com.example.mungmung.mungWiki.repository

import com.example.mungmung.mungWiki.entity.WikiImages
import org.springframework.data.jpa.repository.JpaRepository

interface WikiImagesRepository : JpaRepository<WikiImages,Long> {

    override fun <S : WikiImages?> save(entity: S): S

}