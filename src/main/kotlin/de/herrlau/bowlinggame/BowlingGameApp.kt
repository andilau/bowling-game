package de.herrlau.bowlinggame

object BowlingGameApp {
    private const val TITLE = "Bowling Game App"
    private const val INSTRUCTION = "Enter pins (0..10) for bowling game or exit (-1)."

    private val bowlingGame = BowlingGame()

    @JvmStatic
    fun main(args: Array<String>) {
        println(TITLE)
        println()
        println(INSTRUCTION)
        println()

        while (true) {
            println("$bowlingGame")
            print("Enter number of pins: ")
            val int = readln().toIntOrNull() ?: continue
            if (int == -1) break
            try {
                bowlingGame.roll(int)
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
        println("Score: ${bowlingGame.score}")
    }
}