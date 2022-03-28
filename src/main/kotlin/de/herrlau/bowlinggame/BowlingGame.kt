package de.herrlau.bowlinggame

class BowlingGame {
    private var score = 0

    fun roll(pins: Int) {
        validatePins(pins)

        score += pins
    }

    fun score() = score

    private fun validatePins(pins: Int) {
        if (pins !in 0..10) throw IllegalArgumentException("Invalid pins: $pins")
    }
}