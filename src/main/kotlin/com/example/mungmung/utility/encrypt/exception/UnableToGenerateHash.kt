package com.example.mungmung.utility.encrypt.exception

class UnableToGenerateHash : RuntimeException {
    constructor(message: String?) : super(message) {}
    constructor(message: String?, cause: Throwable?) : super(message, cause) {}
}