package com.apmath.validation.basics.builders

import com.apmath.validation.basics.ValidatorInterface

interface ChainValidatorBuilderInterface {
    fun append(validator: ValidatorInterface): ChainValidatorBuilderInterface

    fun prepend(validator: ValidatorInterface): ChainValidatorBuilderInterface

    fun build(): ValidatorInterface
}
