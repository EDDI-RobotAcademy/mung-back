package com.example.mungmung.mungWiki.controller

import com.example.mungmung.mungWiki.entity.WikiImages
import com.example.mungmung.mungWiki.request.RegisterRequest
import com.example.mungmung.mungWiki.service.MungWikiService
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@Slf4j
@RestController
@RequestMapping("/mungWiki")
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
class MungWikiController {

    @Autowired
    private lateinit var mungWikiService: MungWikiService

    @PostMapping(
        value = ["/register"],
        consumes = [MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE] )
    open fun wikiRegister(
        @RequestPart(value = "images") images: List<MultipartFile>,
        @RequestPart(value = "info") request: RegisterRequest,
        ) : String{

        return mungWikiService.registerWiki(request,images)
    }

}