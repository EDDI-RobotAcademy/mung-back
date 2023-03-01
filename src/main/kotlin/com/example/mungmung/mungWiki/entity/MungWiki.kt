package com.example.mungmung.mungWiki.entity

import jakarta.persistence.*
import lombok.NoArgsConstructor
import lombok.Setter
import org.jetbrains.annotations.NotNull

@Entity
@NoArgsConstructor
@Setter
class MungWiki() {
    constructor(dogType: DogType?, dogImgName: String?, wikiDocument: WikiDocument?, dogStatus: DogStatus?) : this(){
        this.dogType = dogType
        this.dogImgName = dogImgName
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

    @Column(nullable = false)
    @NotNull
    private var dogImgName : String? = null

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    private var wikiDocument : WikiDocument? = null

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    private var dogStatus : DogStatus? = null

}