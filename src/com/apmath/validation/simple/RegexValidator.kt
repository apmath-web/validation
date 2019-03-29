package com.apmath.validation.simple

import com.apmath.validation.simple.exceptions.InvalidArgumentException

class RegexValidator(
    private val pattern: String
) : AbstractValidator() {

    override fun validate(value: Any?): Boolean {
        isValid = true

        if (value !is String) {
            throw InvalidArgumentException("Must be a string")
        }

        if (!Regex(pattern).matches(value)) {
            addMessage("Must match regex: $pattern")
        }

        return isValid!!
    }
}
