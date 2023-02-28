package com.example.mungmung.mungWiki.entity

import jakarta.persistence.*
import lombok.NoArgsConstructor
import lombok.Setter
import org.jetbrains.annotations.NotNull

@Entity
@NoArgsConstructor
@Setter
class WikiDocument() {

    constructor(totalStatus: Long?, documentation: String?) : this(){
        this.totalStatus = totalStatus
        this.documentation = documentation
    }

    @Id
    @Column(name = "wikiDocument_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id : Long? = null

    @Column(nullable = false)
    @NotNull
    private var totalStatus : Long? = null

    @Column(nullable = false)
    @NotNull
    private var documentation : String? =null


    @PrePersist
    private fun validate() {
        require(totalStatus != null && 0<= totalStatus!! || totalStatus!! <= 5) { "totalStatus must be between 0 and 5" }
    }
}