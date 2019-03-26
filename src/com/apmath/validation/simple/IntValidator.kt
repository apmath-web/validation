package com.apmath.validation.simple

import com.apmath.validation.simple.exceptions.InvalidArgumentException

// todo use templates
class IntValidator(
    private val lessThan: Int? = null,
    private val moreThan: Int? = null,
    private val lessThanEquals: Int? = null,
    private val moreThanEquals: Int? = null
) : AbstractValidator() {

    override fun validate(value: Any?): Boolean {
        isValid = true

        val coerced: Int
        try {
            coerced = value as Int
        } catch (e: ClassCastException) {
            throw InvalidArgumentException("Must be a valid Int")
        }

        when {
            lessThan != null && coerced < lessThan -> addMessage("Must be less than $lessThan")
            moreThan != null && coerced > moreThan -> addMessage("Must be more than $moreThan")
            lessThanEquals != null && coerced <= lessThanEquals -> addMessage("Must be less than or equals $lessThanEquals")
            moreThanEquals != null && coerced >= moreThanEquals -> addMessage("Must be more than or equals $moreThanEquals")
        }

        return isValid!!
    }
}
