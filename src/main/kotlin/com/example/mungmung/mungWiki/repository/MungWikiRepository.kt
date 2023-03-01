package com.example.mungmung.mungWiki.repository

import com.example.mungmung.mungWiki.entity.DogType
import com.example.mungmung.mungWiki.entity.MungWiki
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface MungWikiRepository: JpaRepository<MungWiki, Long> {

    override fun <S : MungWiki?> save(entity: S): S

    @Query("select m from MungWiki m where m.dogType = :dogType")
    fun findByDogType(dogType: DogType?): Optional<MungWiki?>?
}