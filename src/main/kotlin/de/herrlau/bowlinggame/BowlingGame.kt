package de.herrlau.bowlinggame

class BowlingGame {

    private val rolls = mutableListOf<Int>()

    internal val frames get() = rolls.frames()

    val isComplete: Boolean
        get() = rolls
            .frames()
            .takeWhile { it.isScoreable() }
            .count() == FRAMES

    val score: Int
        get() = rolls
            .frames()
            .take(FRAMES)
            .flatten()
            .sum()

    fun roll(pins: Int) {
        with((rolls + pins).frames().last()) {
            if (this.isOk())
                rolls += pins
            else
                throw IllegalArgumentException("Invalid pins $pins in frame $this")
        }
    }

    override fun toString(): String =
        "BowlingGame(frames=${frames.toList()} score=${score})"

    private fun List<Int>.frames() = sequence {
        var copy = this@frames.toList()
        while (copy.isNotEmpty()) {
            when {
                copy.isStrike() ->
                    yield(copy.take(3)).also { copy = copy.drop(1) }
                copy.isSpare() ->
                    yield(copy.take(3)).also { copy = copy.drop(2) }
                else ->
                    yield(copy.take(2)).also { copy = copy.drop(2) }
            }
        }
    }

    private fun List<Int>.isValid() = isStrike() || isSpare() || isOpen()

    private fun List<Int>.isScoreable() = when {
        isStrike() || isSpare() -> count() == 3
        isOpen() -> count() == 2
        else -> false
    }

    private fun List<Int>.isOk() = isValid() || isIncomplete()

    private fun List<Int>.isSpare() = take(2).sum() == PINS_MAX

    private fun List<Int>.isStrike() = first() == PINS_MAX

    private fun List<Int>.isOpen() = count() == 2 && take(2).sum() < PINS_MAX

    private fun List<Int>.isIncomplete() = count() == 1 && first() in 0..PINS_MAX

    companion object {
        private const val PINS_MAX = 10
        private const val FRAMES = 10
    }
}
