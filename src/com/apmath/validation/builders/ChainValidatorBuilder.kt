package com.apmath.validation.builders

import com.apmath.validation.ChainValidator
import com.apmath.validation.simple.ValidatorInterface

class ChainValidatorBuilder : ChainValidatorBuilderInterface {
    private val validators: MutableList<ValidatorInterface> = arrayListOf()

    override fun append(validator: ValidatorInterface): ChainValidatorBuilder {
        validators.add(validator)
        return this
    }

    override fun prepend(validator: ValidatorInterface): ChainValidatorBuilder {
        validators.add(0, validator)
        return this
    }

    override fun build(): ValidatorInterface {
        return ChainValidator(validators)
    }
}
