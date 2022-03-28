package de.herrlau.bowlinggame

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Strike")
class BowlingGameLogicStrikeTest {

    @Test
    fun `a bowling game with a single strike and misses should score 10`() {
        with(BowlingGame()) {
            roll(10)
            assertThat(this.score()).isEqualTo(10)
        }
    }

    @Test
    fun `a bowling game with a single strike and bonus 4 2 should score 22`() {
        with(BowlingGame()) {
            roll(10)
            roll(4)
            roll(2)
            assertThat(this.score()).isEqualTo(22)
        }
    }

    @Test
    fun `a bowling game with a two strikes and misses should score 30`() {
        with(BowlingGame()) {
            roll(10)
            roll(10)
            assertThat(this.score()).isEqualTo(30)
        }
    }

    @Test
    fun `a bowling game with a three strikes and misses should score 60`() {
        with(BowlingGame()) {
            roll(10)
            roll(10)
            roll(10)
            assertThat(this.score()).isEqualTo(60)
        }
    }

    @Test
    fun `a bowling game with a 4 strikes and misses should score 60`() {
        with(BowlingGame()) {
            repeat(4) { roll(10) }
            assertThat(this.score()).isEqualTo(90)
        }
    }

    @Test
    fun `a bowling game with a mix of spares and strikes should score 54`() {
        with(BowlingGame()) {
            roll(1)
            roll(9)
            roll(10)
            roll(1)
            roll(9)
            roll(2)
            assertThat(this.score()).isEqualTo(54)
        }
    }
}