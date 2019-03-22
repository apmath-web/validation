package com.apmath.validation.basics.builders

import com.apmath.validation.basics.ChainValidator
import com.apmath.validation.basics.ValidatorInterface

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
