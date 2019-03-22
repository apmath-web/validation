package com.apmath.validation.builders

import com.apmath.validation.ObjectValidator
import com.apmath.validation.simple.ValidatorInterface

class ObjectValidatorBuilder {
    private val chains: MutableMap<String, ChainValidatorBuilderInterface> = hashMapOf()

    fun append(field: String, validator: ValidatorInterface): ObjectValidatorBuilder {
        ensureChainExists(field)

        chains[field]!!.append(validator)

        return this
    }

    fun prepend(field: String, validator: ValidatorInterface): ObjectValidatorBuilder {
        ensureChainExists(field)

        chains[field]!!.prepend(validator)

        return this
    }

    protected fun ensureChainExists(field: String) {
        if (!chains.contains(field)) {
            chains[field] = ChainValidatorBuilder()
        }
    }

    fun build(): ValidatorInterface {
        return ObjectValidator(chains.mapValues { it.value.build() })
    }
}
