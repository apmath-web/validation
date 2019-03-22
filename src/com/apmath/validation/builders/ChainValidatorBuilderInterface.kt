package com.apmath.validation.builders

import com.apmath.validation.simple.ValidatorInterface

interface ChainValidatorBuilderInterface {
    fun append(validator: ValidatorInterface): ChainValidatorBuilderInterface

    fun prepend(validator: ValidatorInterface): ChainValidatorBuilderInterface

    fun build(): ValidatorInterface
}
