package de.herrlau.bowlinggame

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Spare")
class BowlingGameLogicSpareTest {

    @Test
    fun `a bowling game with a spare and missed pins should score 10`() {
        with(BowlingGame()) {
            roll(6)
            roll(4)
            assertThat(this.score).isEqualTo(10)
        }
    }

    @Test
    fun `a bowling game with a spare and a bonus roll of 1 should score 12`() {
        with(BowlingGame()) {
            roll(6)
            roll(4)
            roll(1)
            roll(0)
            assertThat(this.score).isEqualTo(12)
        }
    }

    @Test
    fun `a bowling game with 10 spares and bonus of 6 should score 154`() {
        with(BowlingGame()) {
            repeat(10) {
                roll(6)
                roll(4)
            }
            assertThat(this.score).isEqualTo(16 * 9 + 10)
        }
    }

    @Test
    fun `a bowling game with 10 spares and bonus of 7 should score 146`() {
        with(BowlingGame()) {
            repeat(10) {
                roll(7)
                roll(3)
            }
            assertThat(this.score).isEqualTo(17 * 9 + 10)
        }
    }

}