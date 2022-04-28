package de.herrlau.bowlinggame

import de.herrlau.bowlinggame.BowlingGameConsole.IntegerPrompt

object BowlingGameApp {

    @JvmStatic
    fun main(args: Array<String>) {
            BowlingGameConsole(
                IntegerPrompt(System.`in`, System.out),
                System.out
            )
                .run(BowlingGame())
    }
}