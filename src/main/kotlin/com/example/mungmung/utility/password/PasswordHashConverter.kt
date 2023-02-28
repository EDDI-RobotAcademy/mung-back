package com.example.mungmung.utility.password

import com.example.mungmung.utility.encrypt.EncryptionUtil
import jakarta.persistence.AttributeConverter

class PasswordHashConverter : AttributeConverter<String?, String?> {
    override fun convertToDatabaseColumn(attribute: String?): String {
        return EncryptionUtil.generateHash(attribute)
    }

    override fun convertToEntityAttribute(dbData: String?): String? {
        return dbData
    }
}