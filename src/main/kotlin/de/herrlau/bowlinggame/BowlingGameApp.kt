package de.herrlau.bowlinggame

import java.util.*

object BowlingGameApp {
    private const val TITLE = "Bowling Game App"
    private const val INSTRUCTION = """Enter pins (0..10) for bowling game or exit (-1)."""

    private val scanner = Scanner(System.`in`)
    private val bowlingGame = BowlingGame()

    @JvmStatic
    fun main(args: Array<String>) {
        println(TITLE)
        println()
        println(INSTRUCTION)
        println()

        while (true) {
            print("Number of pins: ")
            val int = scanner.nextInt()
            if (int == -1) break
            try {
                bowlingGame.roll(int)
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
        println("Score: ${bowlingGame.score()}")
    }
}