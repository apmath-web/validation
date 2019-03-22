package com.apmath.validation.basics

interface ValidatorInterface {

    fun validate(value: Any?): Boolean

    fun continueValidation(): Boolean

    val messages: MutableList<MessageInterface>
}
