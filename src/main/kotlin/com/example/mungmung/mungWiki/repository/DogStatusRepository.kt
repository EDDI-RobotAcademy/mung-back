package com.example.mungmung.mungWiki.repository

import com.example.mungmung.mungWiki.entity.DogStatus
import com.example.mungmung.mungWiki.entity.MungWiki
import org.springframework.data.jpa.repository.JpaRepository

interface DogStatusRepository : JpaRepository<DogStatus, Long> {

    override fun <S : DogStatus?> save(entity: S): S

}