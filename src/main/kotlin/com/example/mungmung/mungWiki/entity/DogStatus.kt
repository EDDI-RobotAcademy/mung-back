package com.example.mungmung.mungWiki.entity

import jakarta.persistence.*
import lombok.NoArgsConstructor
import lombok.Setter
import org.jetbrains.annotations.NotNull

@Entity
@NoArgsConstructor
@Setter
class DogStatus() {

    constructor(
        intelligenceLevel: Long?,
        sheddingLevel: Long?,
        sociabilityLevel: Long?,
        activityLevel: Long?,
        indoorAdaptLevel: Long?) : this(){
        this.intelligenceLevel = intelligenceLevel
        this.sheddingLevel = sheddingLevel
        this.sociabilityLevel = sociabilityLevel
        this.activityLevel = activityLevel
        this.indoorAdaptLevel = indoorAdaptLevel
    }

    @Id
    @Column(name = "wikiStatus_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id : Long? = null

    @Column(nullable = false)
    @NotNull
    private var intelligenceLevel : Long? = null

    @Column(nullable = false)
    @NotNull
    private var sheddingLevel : Long? = null

    @Column(nullable = false)
    @NotNull
    private var sociabilityLevel : Long? = null

    @Column(nullable = false)
    @NotNull
    private var activityLevel : Long? = null

    @Column(nullable = false)
    @NotNull
    private var indoorAdaptLevel : Long? = null

    @PrePersist
    private fun validate() {
        require(intelligenceLevel != null &&  0<= intelligenceLevel!! || intelligenceLevel!! <= 5) { "intelligenceLevel must be between 0 and 5" }
        require(sheddingLevel != null &&  0<= sheddingLevel!! || sheddingLevel!! <= 5) { "sheddingLevel must be between 0 and 5" }
        require(sociabilityLevel != null &&  0<= sociabilityLevel!! || sociabilityLevel!! <= 5) { "sociabilityLevel must be between 0 and 5" }
        require(activityLevel != null && 0<= activityLevel!! || activityLevel!! <= 5) { "activityLevel must be between 0 and 5" }
        require(indoorAdaptLevel != null && 0<= indoorAdaptLevel!! ||indoorAdaptLevel!! <= 5) { "indoorAdaptLevel must be between 0 and 5" }
    }
}