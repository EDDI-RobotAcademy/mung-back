package com.example.mungmung.mungWiki.service

import com.example.mungmung.mungWiki.request.RegisterRequest
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
interface MungWikiService {

    fun registerWiki(request: RegisterRequest , images : List<MultipartFile>) : String


}