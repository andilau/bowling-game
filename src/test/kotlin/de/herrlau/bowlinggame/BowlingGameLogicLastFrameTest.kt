package de.herrlau.bowlinggame

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Last Frame")
class BowlingGameLogicLastFrameTest {

    @Test
    fun `a bowling game with mixed spares and strikes strike should score 189`() {
        val score = BowlingGame.from("1/35XXX458/X3/XX6").score()
        assertThat(score).isEqualTo(189)
    }

    @Test
    fun `a bowling game with mixed spares and strikes strike should score 183`() {
        val score = BowlingGame.from("1/35XXX458/X3/XX0").score()
        assertThat(score).isEqualTo(183)
    }

    @Test
    fun `a bowling game with mixed spares and strikes strike should score 173`() {
        val score = BowlingGame.from("1/35XXX458/X3/X").score()
        assertThat(score).isEqualTo(173)
    }

    @Test
    fun `a bowling game with perfect game (all strikes) should score 300`() {
        val actual = BowlingGame.from("XXXXXXXXXXXX").score()
        assertThat(actual).isEqualTo(300)
    }
}