package com.apmath.validation.basics

import com.apmath.validation.basics.exceptions.IncorrectContinueVaidationOrderCallException


abstract class AbstractValidator(
    private val continueValidationOnFail: Boolean = false
) : ValidatorInterface {

    override val messages: MutableList<MessageInterface> = arrayListOf()

    /**
     * Should be set upon validation process
     */
    protected var isValid: Boolean? = false

    override fun continueValidation(): Boolean {
        if (isValid == null) {
            throw IncorrectContinueVaidationOrderCallException()
        }
        if (isValid == false) {
            return continueValidationOnFail
        }
        return true
    }

    protected fun addMessage(message: String)
    {
        isValid = false
        messages.add(Message(message))
    }
}
