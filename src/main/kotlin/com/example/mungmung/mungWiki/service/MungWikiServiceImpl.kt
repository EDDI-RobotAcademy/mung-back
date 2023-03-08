package com.example.mungmung.mungWiki.service

import com.example.mungmung.mungWiki.entity.DogType
import com.example.mungmung.mungWiki.entity.MungWiki
import com.example.mungmung.mungWiki.entity.WikiImages
import com.example.mungmung.mungWiki.repository.DogStatusRepository
import com.example.mungmung.mungWiki.repository.MungWikiRepository
import com.example.mungmung.mungWiki.repository.WikiDocumentRepository
import com.example.mungmung.mungWiki.repository.WikiImagesRepository
import com.example.mungmung.mungWiki.request.RegisterRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.util.*


@Service
class MungWikiServiceImpl:MungWikiService {

    @Autowired
    private lateinit var mungWikiRepository: MungWikiRepository

    @Autowired
    private lateinit var dogStatusRepository: DogStatusRepository

    @Autowired
    private lateinit var wikiDocumentRepository: WikiDocumentRepository

    @Autowired
    private lateinit var wikiImagesRepository : WikiImagesRepository

    override fun registerWiki(request: RegisterRequest , images: List<MultipartFile>) : String{
        return try {
            var dogType : DogType? = DogType.valueOfDogType(request.dogType)
            var maybeMungWiki : Optional<MungWiki?>? = mungWikiRepository.findByDogType(dogType)

            if(maybeMungWiki!!.isPresent){
                "이미 등록된 강아지 종입니다"
            }else{
                val wikiImages = mutableListOf<WikiImages>()

                val dogStatus = request.toDogStatue()
                val wikiDocument = request.toWikiDocument()
                dogStatusRepository.save(dogStatus)
                wikiDocumentRepository.save(wikiDocument)
                val mungWiki = MungWiki(dogType,wikiDocument ,dogStatus)
                mungWikiRepository.save(mungWiki)

                for(image in images ) run {
                    val uuid = UUID.randomUUID()
                    var originalFileName: String? = image.originalFilename
                    var uploadImageName : String = uuid.toString() + originalFileName
                    var imageByte: ByteArray = image.bytes
                    val image = WikiImages(originalFileName,uploadImageName,mungWiki)
                    wikiImages.add(image)
                    try{
                        val writer = FileOutputStream(
                            "../mung-front/src/assets/wikiDog/$uploadImageName"
                        )
                        writer.write(imageByte)
                        writer.close()
                        System.out.println("file upload success")
                    }catch (e : FileNotFoundException){
                        throw RuntimeException(e);
                    }catch (e : IOException){
                        throw RuntimeException(e);
                    }
                }

                for (i in 0..wikiImages.size-1) {
                    wikiImagesRepository.save(wikiImages[i])
                }
                var maybeMungWiki : Optional<MungWiki?>? = mungWikiRepository.findByDogType(dogType)
                var newMungWiki : MungWiki = maybeMungWiki?.get()!!
                newMungWiki.setImages(wikiImages)
                mungWikiRepository.save(newMungWiki)
                "새로운 위키 등록 성공!"
            }
        }catch (e :Exception){
            e.toString()
        }
    }

}