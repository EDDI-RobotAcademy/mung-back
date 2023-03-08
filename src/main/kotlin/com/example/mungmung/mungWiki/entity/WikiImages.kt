package com.example.mungmung.mungWiki.entity

import jakarta.persistence.*
import lombok.NoArgsConstructor
import lombok.Setter

@Entity
@NoArgsConstructor
@Setter
class WikiImages(){

    constructor(originalImageName : String?, uploadFileImageName :String?,mungWiki: MungWiki) : this() {
        this.originalImageName = originalImageName
        this.uploadFileImageName = uploadFileImageName
        this.mungWiki = mungWiki

    }

    @Id
    @Column(name = "wiki_Image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null

    @Column
    private var originalImageName : String? = null

    @Column
    private var uploadFileImageName : String? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MungWiki_id")
    private var mungWiki : MungWiki? = null
}