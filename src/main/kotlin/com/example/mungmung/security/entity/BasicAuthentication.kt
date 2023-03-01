package com.example.mungmung.security.entity

import com.example.mungmung.member.entity.Member
import com.example.mungmung.utility.encrypt.EncryptionUtil
import com.example.mungmung.utility.password.PasswordHashConverter
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity
import lombok.NoArgsConstructor
import lombok.Setter
import lombok.ToString

@Entity
@ToString(callSuper = true)
@NoArgsConstructor
@DiscriminatorValue(Authentication.BASIC_AUTH)
class BasicAuthentication() : Authentication() {
    @Setter
    @Column(nullable = false)
    @Convert(converter = PasswordHashConverter::class)
    private var password: String? = null

    constructor(member: Member, authenticationType: String, password: String?) : this() {
        this.member = member
        this.authenticationType = authenticationType
        this.password = password
    }

    fun isRightPassword(plainToCheck: String?): Boolean {
        return EncryptionUtil.checkValidation(plainToCheck, password)
    }

}