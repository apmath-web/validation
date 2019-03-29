package com.apmath.validation.simple

class NullableValidator(
    private val mustBeNull: Boolean = false
): AbstractValidator() {

    private var continueValidation: Boolean = false

    override fun validate(value: Any?): Boolean {
        isValid = true
        continueValidation = false

        if (value !== null) {
            continueValidation = true
            if (mustBeNull) {
                addMessage("Must be null")
                continueValidation = false
            }
        }

        return isValid!!
    }

    override fun continueValidation(): Boolean {
        return continueValidation
    }
}
