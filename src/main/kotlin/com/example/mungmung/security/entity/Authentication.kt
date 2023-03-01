package com.example.mungmung.security.entity

import com.example.mungmung.member.entity.Member
import jakarta.persistence.*
import lombok.NoArgsConstructor
import lombok.ToString

@Entity
@ToString(exclude = ["member"])
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "authentication_type")
open class Authentication() {
    companion object {
        const val BASIC_AUTH = "BASIC"
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    open var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    open var member: Member? = null

    @Column(name = "authentication_type", nullable = false, insertable = false, updatable = false)
    open var authenticationType: String? = null

    constructor(member: Member, authenticationType: String) : this(){
        this.member = member
        this.authenticationType = authenticationType
    }

}
