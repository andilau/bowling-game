package de.herrlau.bowlinggame

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

@DisplayName("Input Validation")
class BowlingGameInputValidationTest {

    @Test
    fun `a bowling game should take pins from 0-10`() {
        val bowlingGame = BowlingGame()
        bowlingGame.roll(0)
    }

    @TestFactory
    fun testValidPins() =
        listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            .map { pins ->
                DynamicTest.dynamicTest("""A bowling game should accept valid pins: $pins""") {
                    val bowlingGame = BowlingGame()
                    Assertions.assertThatNoException()
                        .isThrownBy { bowlingGame.roll(pins) }
                }
            }

    @TestFactory
    fun testInValidPins() =
        listOf(-1, 11, Int.MIN_VALUE, Int.MAX_VALUE)
            .map { pins ->
                DynamicTest.dynamicTest("""A bowling game should reject invalid pins: $pins""") {
                    val bowlingGame = BowlingGame()
                    Assertions.assertThatIllegalArgumentException()
                        .isThrownBy { bowlingGame.roll(pins) }
                        .withMessage("Invalid number of pins: $pins")
                }
            }
}