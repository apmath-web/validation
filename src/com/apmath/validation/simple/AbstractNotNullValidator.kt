package com.apmath.validation.simple

import com.apmath.validation.simple.exceptions.InvalidArgumentException


abstract class AbstractNotNullValidator(
    continueValidationOnFail: Boolean = false
) : AbstractValidator(continueValidationOnFail) {

    override fun validate(value: Any?): Boolean {
        isValid = true
        if (value == null) {
            throw InvalidArgumentException("Must be not null")
        }
        return validateNotNull(value)
    }

    protected abstract fun validateNotNull(value: Any): Boolean
}
