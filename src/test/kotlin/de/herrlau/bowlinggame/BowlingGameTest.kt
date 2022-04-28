package de.herrlau.bowlinggame

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class BowlingGameTest() {

    lateinit var game: BowlingGame

    @BeforeEach
    fun setUp() {
        game = BowlingGame()
    }

    @Nested
    inner class Score {
        @Test
        fun `a bowling game should be created`() {
            Assertions.assertThatNoException()
                .isThrownBy { BowlingGame() }
        }

        @Test
        fun `a bowling game should take rolls`() {
            Assertions.assertThatNoException()
                .isThrownBy { game.rollMany(0) }
        }

        @Test
        fun `a bowling game without rolls should give a score zero`() {
            assertThat(game.score()).isEqualTo(0)
        }

        @Test
        fun `a bowling game with all rolls zero should give a score zero`() {
            repeat(20) { game.rollMany(0) }
            assertThat(game.score()).isEqualTo(0)
        }

        @Test
        fun `a bowling game with all rolls ones should give a score twenty`() {
            repeat(20) { game.rollMany(1) }
            assertThat(game.score()).isEqualTo(20)
        }

        @Test
        fun `a bowling game with rolls should give a score of sum`() {
            game.rollMany(1, 2, 3)
            assertThat(game.score()).isEqualTo(6)
        }

        @Test
        fun `a bowling game with a spare should add next roll to score of sum`() {
            game.rollMany(5, 5, 6)
            assertThat(game.score()).isEqualTo(22)
        }
    }

    @Nested
    inner class FramesTest {

        @Test
        fun `a bowling game should have no frames for no rolls`() {
            assertThat(game.frames().toList())
                .isEmpty()
        }

        @Test
        fun `a bowling game should have one frame for one roll`() {
            game.rollMany(1)
            assertThat(game.frames().toList())
                .hasSize(1)
                .first().isEqualTo(listOf(1))
        }

        @Test
        fun `a bowling game should have one frame for two rolls`() {
            game.rollMany(1, 2)
            assertThat(game.frames().toList())
                .hasSize(1)
                .first().isEqualTo(listOf(1, 2))
        }

        @Test
        fun `a bowling game should have two frame for three rolls`() {
            game.rollMany(1, 2, 3)
            assertThat(game.frames().toList())
                .isEqualTo(
                    listOf(
                        listOf(1, 2),
                        listOf(3)
                    )
                )
        }

        @Test
        fun `a bowling game should have two frames for with spare`() {
            game.rollMany(5, 5, 6)
            assertThat(game.frames().toList())
                .isEqualTo(
                    listOf(
                        listOf(5, 5, 6),
                        listOf(6)
                    )
                )
        }

    }

    private fun BowlingGame.rollMany(vararg roll: Int) {
        roll.forEach { this.roll(it) }
    }
}
