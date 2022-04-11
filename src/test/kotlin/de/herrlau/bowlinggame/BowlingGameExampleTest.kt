package de.herrlau.bowlinggame

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Example")
class BowlingGameExampleTest {
    private val game = BowlingGame.from("14456/5/X017/6/X2/6")

    @Test
    fun `given example when constructed from notation should score 133`() {
        assertThat(game.score).isEqualTo(133)
    }

    @Test
    fun `given example when constructed from notation should equal string`() {
        assertThat(game.toString())
            .startsWith("BowlingGame(rolls=[1, 4, 4, 5, 6, 4, 5, 5, 10, 0, 1, 7, 3, 6, 4, 10, 2, 8, 6]")
    }
}