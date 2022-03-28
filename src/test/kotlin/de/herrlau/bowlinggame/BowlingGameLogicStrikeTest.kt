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
        val game = BowlingGame.from("X42")
        assertThat(game.score()).isEqualTo(22)
    }

    @Test
    fun `a bowling game with a two strikes and misses should score 30`() {
        val game = BowlingGame.from("XX")
        assertThat(game.score()).isEqualTo(30)
    }

    @Test
    fun `a bowling game with a three strikes and misses should score 60`() {
        val game = BowlingGame.from("XXX")
        assertThat(game.score()).isEqualTo(60)
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
        val game = BowlingGame.from("19X192")
        assertThat(game.score()).isEqualTo(54)
    }
}