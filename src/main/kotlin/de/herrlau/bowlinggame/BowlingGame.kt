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
            yield(copy.take(2))
            copy = copy.drop(2)
        }
    }
}