package com.apmath.validation.simple.builders

import com.apmath.validation.simple.ValidatorInterface

interface ChainValidatorBuilderInterface {
    fun append(validator: ValidatorInterface): ChainValidatorBuilderInterface

    fun prepend(validator: ValidatorInterface): ChainValidatorBuilderInterface

    fun build(): ValidatorInterface
}
