package com.apmath.validation.simple

class RequiredValidator: AbstractValidator() {

    override fun validate(value: Any?): Boolean {
        isValid = true

        if (value == null) {
            addMessage("Must be not null")

            return false
        }

        return isValid!!
    }
}
