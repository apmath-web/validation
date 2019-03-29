package com.apmath.validation.simple

import com.apmath.validation.simple.exceptions.InvalidArgumentException
import org.junit.Test
import kotlin.test.*

class ComparableValidatorTest {
    @Test
    fun testValidatorWithoutConditionsWorkFine() {
        // given
        val validator = ComparableValidator<Int>()

        // when
        val result = validator.validate(2)

        // then
        assertTrue(result)
        assertEquals(0, validator.messages.size)
    }

    @Test
    fun testValidatorWithWrongValueTypeFailed() {
        // given
        val validator = ComparableValidator<Int>(min = 1)

        assertFailsWith<InvalidArgumentException> {
            // when
            validator.validate(Int.MAX_VALUE.toLong()*2)
        }
    }

    @Test
    fun testValidatorWithCorrectConditions() {
        // given
        val validator = ComparableValidator(min = 10, max = 10)

        // when
        val result = validator.validate(10)

        // then
        assertTrue(result)
        assertEquals(0, validator.messages.size)
    }

    @Test
    fun testValidatorWithCorrectExclusiveConditions() {
        // given
        val validator = ComparableValidator(minExclusive = 1, maxExclusive = 3)

        // when
        val result = validator.validate(2)

        // then
        assertTrue(result)
        assertEquals(0, validator.messages.size)
    }

    @Test
    fun testValidatorMinCondition() {
        // given
        val validator = ComparableValidator(min = 20)

        // when
        val result = validator.validate(19)

        // then
        assertFalse(result)
        assertEquals(1, validator.messages.size)
        assertEquals("Must be more than or equals 20", validator.messages[0].message)
    }

    @Test
    fun testValidatorMaxCondition() {
        // given
        val validator = ComparableValidator(max = 20)

        // when
        val result = validator.validate(21)

        // then
        assertFalse(result)
        assertEquals(1, validator.messages.size)
        assertEquals("Must be less than or equals 20", validator.messages[0].message)
    }

    @Test
    fun testValidatorMinExclusiveCondition() {
        // given
        val validator = ComparableValidator(minExclusive = 20)

        // when
        val result = validator.validate(20)

        // then
        assertFalse(result)
        assertEquals(1, validator.messages.size)
        assertEquals("Must be more than 20", validator.messages[0].message)
    }

    @Test
    fun testValidatorMaxExclusiveCondition() {
        // given
        val validator = ComparableValidator(maxExclusive = 20)

        // when
        val result = validator.validate(20)

        // then
        assertFalse(result)
        assertEquals(1, validator.messages.size)
        assertEquals("Must be less than 20", validator.messages[0].message)
    }
}