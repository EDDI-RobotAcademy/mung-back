package com.example.mungmung.member.entity

import com.example.mungmung.security.entity.Authentication
import com.example.mungmung.security.entity.BasicAuthentication
import jakarta.persistence.*
import java.util.*

@Entity
class Member() {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null

    @Column(nullable = false)
    private var email: String? = null

    @Column(nullable = false)
    private var nickname: String? = null

    @Column(nullable = false)
    private var socialType:SocialType? = null

    @Column(nullable = false)
    private var memberType: MemberType? = null

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    private val authentications: Set<Authentication> = HashSet<Authentication>()

    constructor(email: String?, nickname: String?, memberType: String?) : this() {
        this.email = email
        this.nickname = nickname
        when(memberType) {
            MemberType.NORMAL.type -> this.memberType = MemberType.NORMAL
            MemberType.ADMIN.type -> this.memberType = MemberType.ADMIN
        }
        this.socialType = SocialType.LOCAL
    }

    fun getId(): Long? {
        return id
    }

    private fun findBasicAuthentication(): Optional<Authentication> {
        return authentications
                .stream()
                .filter { auth: Authentication? -> auth is BasicAuthentication }
                .findFirst()
    }

    fun isRightPassword(plainToCheck: String): Boolean {
        val maybeBasicAuth: Optional<Authentication> = findBasicAuthentication()
        if (maybeBasicAuth.isPresent) {
            val auth: BasicAuthentication = maybeBasicAuth.get() as BasicAuthentication
            return auth.isRightPassword(plainToCheck)
        }
        return false
    }
}