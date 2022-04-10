package de.herrlau.bowlinggame

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Interface")
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
        Assertions.assertThat(bowlingGame.score).isGreaterThanOrEqualTo(0)
    }

    @Test
    fun `a bowling game should construct from empty notation`() {
        val bowlingGame = BowlingGame.from("")
        Assertions.assertThat(bowlingGame.toString()).isEqualTo("BowlingGame(rolls=[] score=0)")
    }

    @Test
    fun `a bowling game should construct from notation`() {
        val notation = "1/35XXX458/X3/XX6"
        val expected =
            "BowlingGame(rolls=[1, 9, 3, 5, 10, 10, 10, 4, 5, 8, 2, 10, 3, 7, 10, 10, 6] score=189)"
        val bowlingGame = BowlingGame.from(notation)
        Assertions.assertThat(bowlingGame.toString()).isEqualTo(expected)
    }
}