package com.apmath.validation

import com.apmath.validation.simple.*
import com.nhaarman.mockitokotlin2.*
import org.junit.Test
import kotlin.test.*

class ChainValidatorTest {
    @Test
    fun testValidatorChainTriggersValidateOnEveryValidation() {
        // given
        val list: MutableList<ValidatorInterface> = arrayListOf(
            givenContinueValidator(),
            givenContinueValidator(),
            givenContinueValidator()
        )
        val chainValidator = ChainValidator(list)
        val value = 2

        // when
        val result = chainValidator.validate(value)

        // then
        assertTrue(result)
        verify(list[0]).validate(value)
        verify(list[1]).validate(value)
        verify(list[2]).validate(value)
    }

    @Test
    fun testBreakValidatorStopsValidatorChain() {
        // given
        val list: MutableList<ValidatorInterface> = arrayListOf(
            givenContinueValidator(),
            givenNotContinueValidator(),
            givenContinueValidator()
        )
        val chainValidator = ChainValidator(list)
        val value = 4.0

        // when
        val result = chainValidator.validate(value)

        // then
        assertTrue(result)
        verify(list[0]).validate(value)
        verify(list[1]).validate(value)
        verify(list[2], never()).validate(any())
    }

    @Test
    fun testValidatorChainIsValidWhenAllValidatorsInChainBeforeNotContinueIsValid() {
        // given
        val list: MutableList<ValidatorInterface> = arrayListOf(
            givenContinueValidator(),
            givenContinueValidator(),
            givenNotContinueValidator(),
            givenInvalidValidator(arrayListOf("Invalid"))
        )
        val chainValidator = ChainValidator(list)

        // when
        val result = chainValidator.validate(null)

        // then
        assertTrue(result)
    }

    @Test
    fun testValidatorChainIsInvalidWhenAnyValidatorInChainIsInvalid() {
        // given
        val list: MutableList<ValidatorInterface> = arrayListOf(
            givenContinueValidator(),
            givenInvalidValidator(arrayListOf("Invalid")),
            givenContinueValidator()
        )
        val chainValidator = ChainValidator(list)

        // when
        val result = chainValidator.validate(null)

        // then
        assertFalse(result)
    }

    @Test
    fun testValidationChainAggregatesMessagesFromCalledValidators() {
        // given
        val list: MutableList<ValidatorInterface> = arrayListOf(
            givenInvalidValidator(arrayListOf("SomethingWrong", "ReallyWrong"), true),
            givenInvalidValidator(arrayListOf("It is so bad"), false),
            givenInvalidValidator(arrayListOf("It is so bad, but not called"))
        )
        val chainValidator = ChainValidator(list)

        // when
        chainValidator.validate(null)

        // then
        assertEquals(
            arrayListOf("SomethingWrong", "ReallyWrong", "It is so bad"),
            chainValidator.messages.map { it.message }
        )
    }

    private fun givenContinueValidator(): ValidatorInterface {
        return mock {
            on { validate(anyOrNull()) } doReturn true
            on { continueValidation() } doReturn true
        }
    }

    private fun givenNotContinueValidator(): ValidatorInterface {
        return mock {
            on { validate(anyOrNull()) } doReturn true
            on { continueValidation() } doReturn false
        }
    }

    private fun givenInvalidValidator(list: List<String>, continueValidation: Boolean = false): ValidatorInterface {
        return mock {
            on { validate(anyOrNull()) } doReturn false
            on { continueValidation() } doReturn continueValidation
            on { messages } doReturn list.map { Message(it) as MessageInterface }.toMutableList()
        }
    }

}