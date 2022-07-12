package de.herrlau.bowlinggame

import de.herrlau.bowlinggame.BowlingGameConsole.IntegerPrompt
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.mockito.Mockito.anyString
import org.mockito.kotlin.*
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BowlingGameConsoleTest {
    private val output: ByteArrayOutputStream = ByteArrayOutputStream()
    private val asker: IntegerPrompt = mock()

    @BeforeEach
    internal fun setUp() {
        reset(asker)
    }

    @Test
    fun `game console should print title first`() {
        whenever(asker.prompt(anyString())).thenReturn(0)

        BowlingGameConsole(asker, PrintStream(output))
            .run(BowlingGame())

        assertThat(output.toString().lines())
            .first()
            .isEqualTo(BowlingGameConsole.APP_TITLE)
    }

    @Test
    fun `game console should print score twenty when all pins ones`() {
        whenever(asker.prompt(anyString())).doReturn(1)

        BowlingGameConsole(asker, PrintStream(output))
            .run(BowlingGame())

        assertThat(output.toString().lines().dropLastWhile(String::isEmpty))
            .last()
            .isEqualTo("Score: 20")
    }

    @Test
    fun `game console should print score twenty when all pins increasing`() {
        whenever(asker.prompt(anyString())).doReturnConsecutively(
            listOf(0, 1, 2, 3, 4, 5, 6, 5, 4, 3, 2, 1, 0, 1, 2, 3, 4, 5, 0, 0, 0)
        )
        BowlingGameConsole(asker, PrintStream(output))
            .run(BowlingGame())

        assertThat(output.toString().lines().dropLastWhile(String::isEmpty))
            .last()
            .isEqualTo("Score: 49")
    }

    @Test
    fun `game console should print score three-hundred when perfect game`() {
        whenever(asker.prompt(anyString())).doReturnConsecutively(
            List(12) { 10 }
        )
        val console = BowlingGameConsole(asker, PrintStream(output))
        console.run(BowlingGame())
        println("output = $output")
        assertThat(output.toString().lines().dropLastWhile(String::isEmpty))
            .last()
            .isEqualTo("Score: 300")
    }

    @Test
    fun `a game console prompt should prompt and return int`() {
        val inputStream = ByteArrayInputStream("3\n4\n".toByteArray())
        val byteArrayOutputStream = ByteArrayOutputStream()
        val ps = PrintStream(byteArrayOutputStream)
        val integerPrompt = IntegerPrompt(inputStream, ps)
        val message = "Question?"

        val three = integerPrompt.prompt(message)
        assertThat(three).isEqualTo(3)
        assertThat(byteArrayOutputStream.toString()).isEqualTo(message)

        val four = integerPrompt.prompt(message)
        assertThat(four).isEqualTo(4)
        assertThat(byteArrayOutputStream.toString()).isEqualTo(message + message)
    }
}
