package com.example.mungmung.mungWiki.controller

import com.example.mungmung.mungWiki.request.RegisterRequest
import com.example.mungmung.mungWiki.service.MungWikiService
import lombok.extern.slf4j.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@Slf4j
@RestController
@RequestMapping("/mungWiki")
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
class MungWikiController {

    @Autowired
    private lateinit var mungWikiService: MungWikiService

    @PostMapping("/register")
    open fun wikiRegister(@RequestBody registerRequest: RegisterRequest) : String{


        return mungWikiService.registerWiki(registerRequest)
    }
}