package com.example.mungmung.mungWiki.service

import com.example.mungmung.mungWiki.request.RegisterRequest
import org.springframework.stereotype.Service

@Service
interface MungWikiService {

    fun registerWiki(request: RegisterRequest)


}