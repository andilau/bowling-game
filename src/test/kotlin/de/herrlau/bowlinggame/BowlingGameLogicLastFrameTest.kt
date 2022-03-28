package de.herrlau.bowlinggame

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Last Frame")
class BowlingGameLogicLastFrameTest {

    @Test
    fun `test bowling notation`() {
        val notation = "1/35XXX458/X3/XX6"
        assertThat(notation.toRolls())
            .hasSize(notation.length)
            .containsExactly(1, 9, 3, 5, 10, 10, 10, 4, 5, 8, 2, 10, 3, 7, 10, 10, 6)
    }

    @Test
    fun `a bowling game with mixed spares and strikes strike should score 189`() {
        val notation = "1/35XXX458/X3/XX6"
        with(BowlingGame()) {
            notation.toRolls().forEach { pins -> this.roll(pins) }
            assertThat(this.score()).isEqualTo(189)
        }
    }

    @Test
    fun `a bowling game with mixed spares and strikes strike should score 183`() {
        val notation = "1/35XXX458/X3/XX0"
        with(BowlingGame()) {
            notation.toRolls().forEach { pins -> this.roll(pins) }
            assertThat(this.score()).isEqualTo(183)
        }
    }

    @Test
    fun `a bowling game with mixed spares and strikes strike should score 173`() {
        val notation = "1/35XXX458/X3/X"
        with(BowlingGame()) {
            notation.toRolls().forEach { pins -> this.roll(pins) }
            assertThat(this.score()).isEqualTo(173)
        }
    }

    @Test
    fun `a bowling game with all strikes should score 300`() {
        val notation = "XXXXXXXXXXXX"
        with(BowlingGame()) {
            notation.toRolls().forEach { pins -> this.roll(pins) }
            assertThat(this.score()).isEqualTo(300)
        }
    }

    private fun String.toRolls(): List<Int> {
        return filterNot { it.isWhitespace() }
            .mapIndexed() { index, symbol ->
                when (symbol) {
                    in "0123456789" -> symbol.toString().toInt()
                    'X' -> 10
                    '/' -> 10 - this[index - 1].toString().toInt()
                    else -> throw IllegalArgumentException("Invalid character: $symbol")
                }
            }
    }
}