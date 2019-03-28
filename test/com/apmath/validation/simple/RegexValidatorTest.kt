package com.apmath.validation.simple

import com.apmath.validation.simple.exceptions.InvalidArgumentException
import org.junit.Test
import kotlin.test.*

class RegexValidatorTest {
    @Test
    fun testValidatorSucceedsWithCorrectValue() {
        // given
        val validator = RegexValidator("^[0-9]+$")

        // when
        val result = validator.validate("9234898124")

        // then
        assertTrue(result)
        assertEquals(0, validator.messages.size)
    }

    @Test
    fun testValidatorFailsWithIncorrectValue() {
        // given
        val validator = RegexValidator("^[0-9]+$")

        // when
        val result = validator.validate("923a4898124")

        // then
        assertTrue(result)
        assertEquals(0, validator.messages.size)
    }

    @Test
    fun testValidatorThrowsExceptionOnTypeFailed() {
        // given
        val validator = RegexValidator("^[0-9]+$")

        assertFailsWith<InvalidArgumentException> {
            // when
            validator.validate(1)
        }
    }
}
