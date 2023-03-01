package com.example.mungmung.mungWiki.service

import com.example.mungmung.mungWiki.entity.DogType
import com.example.mungmung.mungWiki.entity.MungWiki
import com.example.mungmung.mungWiki.repository.DogStatusRepository
import com.example.mungmung.mungWiki.repository.MungWikiRepository
import com.example.mungmung.mungWiki.repository.WikiDocumentRepository
import com.example.mungmung.mungWiki.request.RegisterRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MungWikiServiceImpl:MungWikiService {

    @Autowired
    private lateinit var mungWikiRepository: MungWikiRepository

    @Autowired
    private lateinit var dogStatusRepository: DogStatusRepository

    @Autowired
    private lateinit var wikiDocumentRepository: WikiDocumentRepository

    override fun registerWiki(request: RegisterRequest) : String{
        return try {
            val dogStatus = request.toDogStatue()
            val wikiDocument = request.toWikiDocument()

            dogStatusRepository.save(dogStatus)
            wikiDocumentRepository.save(wikiDocument)

            var dogType : DogType? = DogType.valueOfDogType(request.dogType)
            val mungWiki = MungWiki(dogType,request.dogImgName,wikiDocument ,dogStatus)
            mungWikiRepository.save(mungWiki)

            "새로운 위키 등록 성공!"
        }catch (e :Exception){
            e.toString()
        }
    }

}