package com.apmath.validation

import com.apmath.validation.simple.AbstractValidator
import com.apmath.validation.simple.ValidatorInterface

class ChainValidator(
    private val validators: List<ValidatorInterface>
): AbstractValidator() {

    override fun validate(value: Any?): Boolean {
        isValid = true

        for (validator in validators) {
            if (!validator.validate(value)) {
                isValid = false
                messages.addAll(validator.messages)
            }
            if (!validator.continueValidation()) {
                break
            }
        }

        return isValid!!
    }
}
