package de.herrlau.bowlinggame

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BowlingGameTest {

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
        assertThat(bowlingGame.score()).isGreaterThanOrEqualTo(0)
    }

}