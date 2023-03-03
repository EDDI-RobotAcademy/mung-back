package com.example.mungmung.walkingDog.entity

import com.example.mungmung.member.entity.Member
import jakarta.persistence.*
import lombok.NoArgsConstructor
import lombok.Setter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle


@Entity
@NoArgsConstructor
@Setter
class WalkingDog() {

    @Id
    @Column(name = "walking_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    var member: Member? = null

    @Column(nullable = false)
    private var thumbnailPath: String? = null

    @Column(nullable = false)
    private var title: String? = null

    @Column(nullable = false)
    private var content: String? = null

    @Column(nullable = false)
    private var writer: String? = null

    @Embedded
    private var walkingDogInfo: WalkingDogInfo? = null

    @Column(nullable = false)
    private var regDate = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT))

    constructor(thumbnailPath: String?, title: String?, content: String?, writer: String?, walkingDogInfo: WalkingDogInfo) : this() {
        this.thumbnailPath = thumbnailPath
        this.title = title
        this.content = content
        this.writer = writer
        this.walkingDogInfo = walkingDogInfo
    }
}