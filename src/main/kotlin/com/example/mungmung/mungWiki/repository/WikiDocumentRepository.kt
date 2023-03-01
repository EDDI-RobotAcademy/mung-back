package com.example.mungmung.mungWiki.repository

import com.example.mungmung.mungWiki.entity.WikiDocument
import org.springframework.data.jpa.repository.JpaRepository

interface WikiDocumentRepository: JpaRepository<WikiDocument,Long> {

    override fun <S : WikiDocument?> save(entity: S): S

}