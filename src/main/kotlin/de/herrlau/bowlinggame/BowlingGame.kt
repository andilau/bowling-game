package de.herrlau.bowlinggame

class BowlingGame {
    private val rolls = mutableListOf<Int>()
    fun roll(pins: Int) {
        validatePins(pins)
        rolls += pins
        if (pins == MAX_PINS) rolls += MISS // add another miss to align frame for now
    }

    fun score() =
        rolls
            .windowed(FRAME_SIZE, FRAME_SIZE, true)
            .mapIndexed() { index, frame ->
                when {
                    frame.isStrike() -> MAX_PINS + bonusStrike(index.inc())
                    frame.isSpare() -> MAX_PINS + bonusSpare(index.inc())
                    else -> frame.sum()
                }
            }
            .sum()

    private fun bonusSpare(frame: Int) = getFirstOfFrame(frame)

    private fun bonusStrike(frame: Int) = when {
        getSecondOfFrame(frame) == 0 -> getFirstOfFrame(frame) + getFirstOfFrame(frame + 1) // Strike
        else -> getFirstOfFrame(frame) + getSecondOfFrame(frame)
    }

    private fun getFirstOfFrame(index: Int) =
        if ((index * FRAME_SIZE) in rolls.indices) rolls[index * 2] else MISS

    private fun getSecondOfFrame(index: Int) =
        if ((index * FRAME_SIZE + 1) in rolls.indices) rolls[index * 2 + 1] else MISS

    private fun List<Int>.isStrike() = first() == MAX_PINS

    private fun List<Int>.isSpare() = sum() == MAX_PINS

    private fun validatePins(pins: Int) {
        if (pins !in MISS..MAX_PINS) throw IllegalArgumentException("Invalid pins: $pins")
    }

    companion object {
        private const val MAX_PINS = 10
        private const val MISS = 0
        private const val FRAME_SIZE = 2
    }
}