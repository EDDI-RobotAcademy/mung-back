package com.example.mungmung.mungWiki.service

import com.example.mungmung.mungWiki.entity.DogType
import com.example.mungmung.mungWiki.entity.MungWiki
import com.example.mungmung.mungWiki.repository.DogStatusRepository
import com.example.mungmung.mungWiki.repository.MungWikiRepository
import com.example.mungmung.mungWiki.repository.WikiDocumentRepository
import com.example.mungmung.mungWiki.request.RegisterRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

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
            var dogType : DogType? = DogType.valueOfDogType(request.dogType)
            var maybeMungWiki : Optional<MungWiki?>? = mungWikiRepository.findByDogType(dogType)

            if(maybeMungWiki!!.isPresent){
                "이미 등록된 강아지 종입니다"
            }else{

                val dogStatus = request.toDogStatue()
                val wikiDocument = request.toWikiDocument()
                dogStatusRepository.save(dogStatus)
                wikiDocumentRepository.save(wikiDocument)

                val mungWiki = MungWiki(dogType,request.dogImgName,wikiDocument ,dogStatus)
                mungWikiRepository.save(mungWiki)

                "새로운 위키 등록 성공!"
            }
        }catch (e :Exception){
            e.toString()
        }
    }

}