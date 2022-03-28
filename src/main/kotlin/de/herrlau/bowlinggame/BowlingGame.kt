package de.herrlau.bowlinggame

class BowlingGame {
    var score = 0

    fun roll(pins: Int) {
        score += pins
    }

    fun score() = score
}