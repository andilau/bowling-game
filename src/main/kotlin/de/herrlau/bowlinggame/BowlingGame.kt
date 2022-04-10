package de.herrlau.bowlinggame

class BowlingGame {

    private val rolls = mutableListOf<Int>()

    fun roll(pins: Int) {
        if (pins !in PINS_MISS..PINS_MAX)
            throw IllegalArgumentException("Invalid number of pins: $pins")
        rolls += pins
        try {
            rolls.calculate()
        } catch (e: IllegalArgumentException) {
            rolls.removeLast()
            throw e
        }
    }

    val score
        get() = rolls.calculate()

    override fun toString(): String =
        "BowlingGame(rolls=$rolls score=" +
                kotlin.runCatching { score }.getOrNull() + ")"

    private fun List<Int>.calculate(frame: Int = 1): Int =
        if (isNotEmpty() && frame <= 10)
            when {
                isStrike() -> get(0) + getOrElse(1) { 0 } + getOrElse(2) { 0 } + drop(1).calculate(frame + 1)
                isSpare() -> get(0) + get(1) + getOrElse(2) { 0 } + drop(2).calculate(frame + 1)
                isOpen() -> get(0) + get(1) + drop(2).calculate(frame + 1)
                isInvalid() -> throw IllegalArgumentException("Invalid frame $frame: $this")
                isIncomplete() -> get(0) + drop(1).calculate(frame + 1)
                else -> error("Incomplete frame $frame: $this")
            }
        //.also { println("$frame: $this + $it") }
        else 0

    private fun List<Int>.isStrike() = isNotEmpty() && first() == PINS_MAX

    private fun List<Int>.isSpare() = size >= 2 && !isStrike() && take(2).sum() == PINS_MAX

    private fun List<Int>.isOpen() = size >= 2 && this[0] + this[1] < PINS_MAX

    private fun List<Int>.isInvalid() = size >= 2 && this[0] + this[1] > PINS_MAX

    private fun List<Int>.isIncomplete() = size == 1

    companion object {
        private const val PINS_MAX = 10
        private const val PINS_MISS = 0

        fun from(notation: String) =
            BowlingGame().apply {
                notation.toRolls().forEach { pins -> this.roll(pins) }
            }

        private fun String.toRolls(): List<Int> =
            mapIndexed { index, symbol ->
                when (symbol) {
                    in "0123456789" -> symbol.toString().toInt()
                    '-' -> PINS_MISS
                    'X' -> PINS_MAX
                    '/' -> PINS_MAX - this[index - 1].toString().toInt()
                    else -> throw IllegalArgumentException("Invalid character: $symbol")
                }
            }
    }
}
