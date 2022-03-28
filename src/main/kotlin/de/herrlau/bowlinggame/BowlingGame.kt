package de.herrlau.bowlinggame

class BowlingGame {
    private val rolls = mutableListOf<Int>()
    fun roll(pins: Int) {
        validatePins(pins)
        rolls += pins
    }

    fun score() =
        rolls
            .windowed(FRAME_SIZE, FRAME_SIZE, true)
            .mapIndexed() { index, frame ->
                val pinsSum = frame.sum()
                when {
                    pinsSum < MAX_PINS -> pinsSum
                    pinsSum == MAX_PINS -> MAX_PINS + bonusSpare(index.inc())
                    else -> TODO()
                }
            }
            .sum()

    private fun bonusSpare(index: Int) =
        if ((FRAME_SIZE * index) in rolls.indices) rolls[FRAME_SIZE * index] else 0

    private fun validatePins(pins: Int) {
        if (pins !in MISS..MAX_PINS) throw IllegalArgumentException("Invalid pins: $pins")
    }

    companion object {
        private const val MAX_PINS = 10
        private const val MISS = 0
        private const val FRAME_SIZE = 2
    }
}