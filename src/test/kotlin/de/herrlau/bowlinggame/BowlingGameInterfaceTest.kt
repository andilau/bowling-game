package de.herrlau.bowlinggame

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class BowlingGameInterfaceTest {
    @Test
    fun `a bowling game should be created`() {
        Assertions.assertThatNoException()
            .isThrownBy { BowlingGame() }
    }

    @Test
    fun `a bowling game should take rolls`() {
        val bowlingGame = BowlingGame()
        Assertions.assertThatNoException()
            .isThrownBy { bowlingGame.roll(0) }
    }

    @Test
    fun `a bowling game should give a score`() {
        val bowlingGame = BowlingGame()
        Assertions.assertThat(bowlingGame.score()).isGreaterThanOrEqualTo(0)
    }
}
