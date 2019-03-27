package com.apmath.validation.simple

import com.apmath.validation.simple.exceptions.InvalidArgumentException

class ComparableValidator<in T : Comparable<T>>(
    private val max: T? = null,
    private val min: T? = null,
    private val maxExclusive: T? = null,
    private val minExclusive: T? = null
) : AbstractNotNullValidator() {

    override fun validateNotNull(value: Any): Boolean {
        // todo determine how to check parameter type
        try {
            validateCoerced(value as T)
        } catch (e: ClassCastException) {
            throw InvalidArgumentException("Incorrect type")
        }
        return isValid!!
    }

    private fun validateCoerced(value: T) {
        when {
            maxExclusive != null && value >= maxExclusive -> addMessage("Must be less than $maxExclusive")
            minExclusive != null && value <= minExclusive -> addMessage("Must be more than $minExclusive")
            max != null && value > max -> addMessage("Must be less than or equals $max")
            min != null && value < min -> addMessage("Must be more than or equals $min")
        }
    }
}
