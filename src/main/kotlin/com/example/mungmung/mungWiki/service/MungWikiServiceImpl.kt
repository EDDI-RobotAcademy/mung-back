package com.example.mungmung.mungWiki.service

import com.example.mungmung.mungWiki.entity.DogStatus
import com.example.mungmung.mungWiki.entity.MungWiki
import com.example.mungmung.mungWiki.repository.DogStatusRepository
import com.example.mungmung.mungWiki.repository.MungWikiRepository
import com.example.mungmung.mungWiki.repository.WikiDocumentRepository
import com.example.mungmung.mungWiki.request.RegisterRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MungWikiServiceImpl {

    @Autowired
    private lateinit var mungWikiRepository: MungWikiRepository

    @Autowired
    private lateinit var dogStatusRepository: DogStatusRepository

    @Autowired
    private lateinit var wikiDocumentRepository: WikiDocumentRepository

    fun registerWiki(request: RegisterRequest) {

        val dogStatus = request.toDogStatue()
        val wikiDocument = request.toWikiDocument()

        dogStatusRepository.save(dogStatus)
        wikiDocumentRepository.save(wikiDocument)


        val mungWiki = MungWiki(request.dogType,request.dogImgName,wikiDocument ,dogStatus)
        mungWikiRepository.save(mungWiki)
    }

}