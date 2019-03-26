package com.apmath.validation.simple

import com.apmath.validation.simple.exceptions.InvalidArgumentException

// todo use templates
class LongValidator(
    private val lessThan: Long? = null,
    private val moreThan: Long? = null,
    private val lessThanEquals: Long? = null,
    private val moreThanEquals: Long? = null
) : AbstractValidator() {

    override fun validate(value: Any?): Boolean {
        isValid = true

        val coerced: Long
        try {
            coerced = value as Long
        } catch (e: ClassCastException) {
            throw InvalidArgumentException("Must be a valid Long")
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
