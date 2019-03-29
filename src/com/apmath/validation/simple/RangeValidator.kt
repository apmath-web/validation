package com.apmath.validation.simple

import com.apmath.validation.simple.exceptions.InvalidArgumentException

class RangeValidator<T : Comparable<T>>(private val range: Set<T>) : AbstractValidator() {

    override fun validate(value: Any?): Boolean {
        isValid = true

        val coerced: T
        try {
            coerced = value as T
        } catch (e: ClassCastException) {
            throw InvalidArgumentException("Must be a valid type")
        }

        when {
            !range.contains(coerced) -> addMessage("Must be in range $range")
        }

        return isValid!!
    }
}
