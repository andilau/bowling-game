package de.herrlau.bowlinggame

import org.assertj.core.api.Assertions.*
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
    inner class ScoreTest {
        @Test
        fun `a bowling game should be created`() {
            assertThatNoException()
                .isThrownBy { BowlingGame() }
        }

        @Test
        fun `a bowling game should take rolls`() {
            assertThatNoException()
                .isThrownBy { game.roll(0) }
        }

        @Test
        fun `a bowling game without rolls should give a score zero`() {
            assertThat(game.score).isEqualTo(0)
        }

        @Test
        fun `a bowling game with all rolls zero should give a score zero`() {
            repeat(20) { game.rollMany(0) }
            assertThat(game.score).isEqualTo(0)
        }

        @Test
        fun `a bowling game with all rolls ones should give a score twenty`() {
            repeat(20) { game.rollMany(1) }
            assertThat(game.score).isEqualTo(20)
        }

        @Test
        fun `a bowling game with rolls should give a score of sum`() {
            game.rollMany(1, 2, 3)
            assertThat(game.score).isEqualTo(6)
        }

        @Test
        fun `a bowling game with a spare should add next roll to score of sum`() {
            game.rollMany(5, 5, 6)
            assertThat(game.score).isEqualTo(22)
        }

        @Test
        fun `a bowling game with a strike should add next two roll to score of sum`() {
            game.rollMany(10, 5, 4)
            assertThat(game.score).isEqualTo(28)
        }

        @Test
        fun `a bowling game with alls strike should result in perfect score`() {
            repeat(21) { game.roll(10) }
            assertThat(game.score).isEqualTo(300)
        }
    }

    @Nested
    inner class FramesTest {

        @Test
        fun `a bowling game should have no frames for no rolls`() {
            assertThat(game.frames.toList())
                .isEmpty()
        }

        @Test
        fun `a bowling game should have one frame for one roll`() {
            game.rollMany(1)
            assertThat(game.frames.toList())
                .hasSize(1)
                .first().isEqualTo(listOf(1))
        }

        @Test
        fun `a bowling game should have one frame for two rolls`() {
            game.rollMany(1, 2)
            assertThat(game.frames.toList())
                .hasSize(1)
                .first().isEqualTo(listOf(1, 2))
        }

        @Test
        fun `a bowling game should have two frame for three rolls`() {
            game.rollMany(1, 2, 3)
            assertThat(game.frames.toList())
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
            assertThat(game.frames.toList())
                .isEqualTo(
                    listOf(
                        listOf(5, 5, 6),
                        listOf(6)
                    )
                )
        }

        @Test
        fun `a bowling game should have two frames for with strike`() {
            game.rollMany(10, 5, 4)
            assertThat(game.frames.toList())
                .isEqualTo(
                    listOf(
                        listOf(10, 5, 4),
                        listOf(5, 4)
                    )
                )
        }
    }

    @Nested
    inner class ValidationTest {
        @Test
        fun `a bowling game with 3 rolls is incomplete`() {
            game.rollMany(5, 5, 6)
            assertThat(game.isComplete).isFalse
        }

        @Test
        fun `a bowling game with twenty rolls of ones is complete`() {
            repeat(20) { game.roll(1) }
            assertThat(game.isComplete).isTrue
        }

        @Test
        fun `a bowling game with ten strikes is incomplete`() {
            repeat(10) { game.roll(10) }
            assertThat(game.isComplete).isFalse
        }

        @Test
        fun `a bowling game with twelve strikes is complete`() {
            repeat(12) { game.roll(10) }
            assertThat(game.isComplete).isTrue
        }

        @Test
        fun `a bowling should not accept pins out of range 0-10`() {
            assertThatIllegalArgumentException()
                .isThrownBy { game.roll(11) }
                .withMessage("Invalid pins 11 in frame [11]")
        }

        @Test
        fun `a bowling should not accept second pins leading to sum greater 10`() {
            game.roll(1)
            assertThatIllegalArgumentException()
                .isThrownBy { game.roll(10) }
                .withMessage("Invalid pins 10 in frame [1, 10]")
        }
    }

    private fun BowlingGame.rollMany(vararg roll: Int) {
        roll.forEach { this.roll(it) }
    }
}
