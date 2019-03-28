package com.apmath.validation.simple

import com.apmath.validation.simple.exceptions.InvalidArgumentException

class StringValidator(
    private val minLen: Int? = null,
    private val maxLen: Int? = null
) : AbstractValidator() {

    override fun validate(value: Any?): Boolean {
        isValid = true

        if (value !is String) {
            throw InvalidArgumentException("Must be a string")
        }

        when {
            minLen != null && value.length < minLen -> addMessage("Must contain $minLen symbols at least")
            maxLen != null && value.length > maxLen -> addMessage("Must contain $maxLen symbols at most")
        }

        return isValid!!
    }
}
