package de.herrlau.bowlinggame

import java.io.InputStream
import java.io.PrintStream
import java.util.*

class BowlingGameConsole(
    private val prompt: IntegerPrompt,
    private val output: PrintStream
) {

    fun run(game: BowlingGame) {
        output.println(APP_TITLE)
        output.println()
        output.println(APP_INSTRUCTIONS)
        output.println()

        while (!game.isComplete) {
            try {
                val pins = prompt.prompt(APP_PROMPT)
                game.roll(pins)
                output.println(game)
            } catch (e: IllegalArgumentException) {
                output.println(e.message)
            }
        }
        output.println("Score: ${game.score}")
    }

    companion object {
        const val APP_TITLE = "Bowling Game App"
        const val APP_INSTRUCTIONS = "Enter pins (0..10) for bowling game."
        const val APP_PROMPT = "Please, enter pins: "
    }

    class IntegerPrompt(
        input: InputStream,
        private val output: PrintStream
    ) {
        private val scanner = Scanner(input)

        fun prompt(message: String): Int {
            output.print(message)
            return scanner.nextInt()
        }
    }
}
