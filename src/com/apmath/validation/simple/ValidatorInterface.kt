package com.apmath.validation.simple

interface ValidatorInterface {

    fun validate(value: Any?): Boolean

    fun continueValidation(): Boolean

    val messages: MutableList<MessageInterface>
}
