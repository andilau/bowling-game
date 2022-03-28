package de.herrlau.bowlinggame

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class BowlingGameLogicSimple {

    @Test
    fun `a bowling game without pins rolled should score 0`() {
        val bowlingGame = BowlingGame()
        Assertions.assertThat(bowlingGame.score()).isEqualTo(0)
    }

    @Test
    fun `a bowling game with all pins missed should score 0`() {
        val bowlingGame = BowlingGame()
        repeat(20) {
            bowlingGame.roll(0)
        }
        Assertions.assertThat(bowlingGame.score()).isEqualTo(0)
    }

    @Test
    fun `a bowling game with only 1 pin rolled should score 1`() {
        val bowlingGame = BowlingGame()
        bowlingGame.roll(1)
        repeat(19) {
            bowlingGame.roll(0)
        }
        Assertions.assertThat(bowlingGame.score()).isEqualTo(1)
    }

    @Test
    fun `a bowling game with all 1 pins rolled should score 20`() {
        val bowlingGame = BowlingGame()
        repeat(20) {
            bowlingGame.roll(1)
        }
        Assertions.assertThat(bowlingGame.score()).isEqualTo(20)
    }

    @Test
    fun `a bowling game with 4 and 5 pins rolled should score 9`() {
        with(BowlingGame()) {
            roll(4)
            roll(5)
            Assertions.assertThat(this.score()).isEqualTo(9)
        }
    }

    @Test
    fun `a bowling game with repeatedly 4 and 5 pins rolled should score 90`() {
        with(BowlingGame()) {
            repeat(10) {
                roll(4)
                roll(5)
            }
            Assertions.assertThat(this.score()).isEqualTo(90)
        }
    }
}