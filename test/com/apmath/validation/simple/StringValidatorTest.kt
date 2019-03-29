package com.apmath.validation.simple

import com.apmath.validation.simple.exceptions.InvalidArgumentException
import org.junit.Test
import kotlin.test.*

class StringValidatorTest {
    @Test
    fun testValidatorWithoutConditionsWorkFine() {
        // given
        val validator = StringValidator()

        // when
        val result = validator.validate("Some string")

        // then
        assertTrue(result)
        assertEquals(0, validator.messages.size)
    }

    @Test
    fun testValidatorWithWrongValueTypeFailed() {
        // given
        val validator = StringValidator()

        assertFailsWith<InvalidArgumentException> {
            // when
            validator.validate(1)
        }
    }

    @Test
    fun testValidatorWithCorrectConditions() {
        // given
        val validator = StringValidator(minLen = 10, maxLen = 10)

        // when
        val result = validator.validate("10 symbols")

        // then
        assertTrue(result)
        assertEquals(0, validator.messages.size)
    }

    @Test
    fun testValidatorMinLenCondition() {
        // given
        val validator = StringValidator(minLen = 18)

        // when
        val result = validator.validate("17 symbols length")

        // then
        assertFalse(result)
        assertEquals(1, validator.messages.size)
        assertEquals("Must contain 18 symbols at least", validator.messages[0].message)
    }

    @Test
    fun testValidatorMaxCondition() {
        // given
        val validator = StringValidator(maxLen = 24)

        // when
        val result = validator.validate("25 symbols in that string")

        // then
        assertFalse(result)
        assertEquals(1, validator.messages.size)
        assertEquals("Must contain 24 symbols at most", validator.messages[0].message)
    }
}