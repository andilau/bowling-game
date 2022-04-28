package de.herrlau.bowlinggame

class BowlingGame {

    private val rolls = mutableListOf<Int>()

    fun roll(pins: Int) {
        rolls += pins
    }

    fun score(): Int = frames().flatten().sum()

    fun frames() = sequence {
        var copy = rolls.toList()
        while (copy.isNotEmpty()) {
            if (copy.isStrike() || copy.isSpare())
                yield(copy.take(3))
            else
                yield(copy.take(2))

            if (copy.isStrike())
                copy = copy.drop(1)
            else
                copy = copy.drop(2)
        }
    }

    private fun List<Int>.isSpare(): Boolean = take(2).sum() == 10
    private fun List<Int>.isStrike(): Boolean = first() == 10
}
