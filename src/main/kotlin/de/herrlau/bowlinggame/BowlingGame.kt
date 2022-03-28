package de.herrlau.bowlinggame

class BowlingGame {
    private val rolls = mutableListOf<Int>()

    fun roll(pins: Int) {
        rolls.validatePins(pins)
        rolls += pins
    }

    fun score() =
        rolls
            .toFrames()
            .foldIndexed(0) { index, score, frame ->
                score + when {
                    frame.isStrike() -> MAX_PINS + bonus(index, 2)
                    frame.isSpare() -> MAX_PINS + bonus(index, 1)
                    else -> frame.sum()
                }
            } + bonusLastFrame()

    private fun List<Int>.validatePins(pins: Int) {
        if (pins !in MISS..MAX_PINS) throw IllegalArgumentException("Invalid number of pins: $pins")

        toFrames()
            .lastOrNull()
            ?.let { frame ->
                if (frame.isOpen() && frame.first() + pins > MAX_PINS)
                    throw IllegalArgumentException("Invalid number of pins for open frame $frame: $pins")
            }
    }

    private fun List<Int>.toFrames() = sequence {
        val list = this@toFrames
        var current = 0
        for (frameIndex in 1..NORMAL_FRAMES) {
            when {
                // Strike
                current <= list.lastIndex && list[current] == MAX_PINS ->
                    yield(listOf(MAX_PINS))
                        .also { current++ }
                // Spare
                current + 1 <= list.lastIndex && list[current] + list[current + 1] <= MAX_PINS ->
                    yield(list.subList(current, current + FRAME_SIZE))
                        .also { current += FRAME_SIZE }
                // Open
                current <= list.lastIndex && list[current] < MAX_PINS ->
                    yield(list.subList(current, current + 1))
                        .also { current += FRAME_SIZE }
            }
        }
        when {
            // Strike with Bonus
            current + 2 <= lastIndex && get(current) == MAX_PINS ->
                yield(subList(current, current + BONUS_FRAME_SIZE))
            // Spare with Bonus
            current + 2 <= lastIndex && get(current) + get(current + 1) == MAX_PINS ->
                yield(subList(current, current + BONUS_FRAME_SIZE))
            // No Bonus
            current + 1 <= lastIndex ->
                yield(subList(current, current + FRAME_SIZE))
            // Open Bonus for Strike
            current <= list.lastIndex && list[current] == MAX_PINS ->
                yield(listOf(MAX_PINS))
        }
    }

    override fun toString(): String =
        "BowlingGame(rolls=$rolls) score=${this.score()}"

    private fun bonus(frame: Int, bonus: Int) = rolls
        .toFrames()
        .drop(frame + 1)
        .flatten()
        .take(bonus)
        .sum()

    private fun bonusLastFrame(): Int = rolls
        .toFrames()
        .drop(NORMAL_FRAMES)
        .filter { it.size == BONUS_FRAME_SIZE }
        .firstOrNull()
        ?.let { bonus ->
            when {
                bonus.isSpare() -> bonus[2]
                bonus.isStrike() -> bonus[1] + bonus[2]
                else -> MISS
            }
        } ?: MISS

    private fun List<Int>.isStrike() = first() == MAX_PINS

    private fun List<Int>.isSpare() = !isStrike() && sum() == MAX_PINS

    private fun List<Int>.isOpen() = !isStrike() && size == 1

    companion object {
        private const val MAX_PINS = 10
        private const val MISS = 0
        private const val NORMAL_FRAMES = 9
        private const val FRAME_SIZE = 2
        private const val BONUS_FRAME_SIZE = 3

        fun from(notation: String) =
            BowlingGame().apply {
                notation.toRolls().forEach { pins -> this.roll(pins) }
            }

        private fun String.toRolls(): List<Int> =
            mapIndexed { index, symbol ->
                when (symbol) {
                    in "0123456789" -> symbol.toString().toInt()
                    '-' -> MISS
                    'X' -> MAX_PINS
                    '/' -> MAX_PINS - this[index - 1].toString().toInt()
                    else -> throw IllegalArgumentException("Invalid character: $symbol")
                }
            }
    }
}