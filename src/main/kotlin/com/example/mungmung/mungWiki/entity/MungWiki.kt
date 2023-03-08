package com.example.mungmung.mungWiki.entity

import jakarta.persistence.*
import lombok.NoArgsConstructor
import lombok.Setter
import org.jetbrains.annotations.NotNull


@Entity
@NoArgsConstructor
@Setter
class MungWiki() {
    constructor(dogType: DogType?, wikiDocument: WikiDocument?, dogStatus: DogStatus?) : this(){
        this.dogType = dogType
        this.wikiDocument = wikiDocument
        this.dogStatus = dogStatus
    }

    @Id
    @Column(name = "wiki_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id : Long? = null

    @Column(nullable = false)
    @NotNull
    private var dogType : DogType? = null

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    private var wikiDocument : WikiDocument? = null

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    private var dogStatus : DogStatus? = null

    @NotNull
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mungWiki")
    private var wikiImages: List<WikiImages> = ArrayList()

    fun setImages(wikiImages: List<WikiImages>){
        this.wikiImages = wikiImages
    }
}