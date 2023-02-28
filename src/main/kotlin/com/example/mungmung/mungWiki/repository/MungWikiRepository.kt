package com.example.mungmung.mungWiki.repository

import com.example.mungmung.mungWiki.entity.MungWiki
import org.springframework.data.jpa.repository.JpaRepository

interface MungWikiRepository: JpaRepository<MungWiki, Long> {

    override fun <S : MungWiki?> save(entity: S): S
}