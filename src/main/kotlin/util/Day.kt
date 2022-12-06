package util

import kotlin.io.path.Path
import kotlin.io.path.readLines

/** A day. */
public abstract class Day(private val day: Int) {

    /** Runs the day. */
    public fun run(test: Boolean = false) {
        fun runPart(part: Int) {
            require(part in 1..2)

            println("Running Day $day, Part $part...")
            val input = Path("src", "main", "resources", "day$day${ if (test) { "_test" } else { "" } }.txt").readLines()

            val expectedOutput = if (test) { input[input.size + (part - 3)] } else { null }
            val actualInput = if (test) { input.dropLast(2) } else { input }

            val output = if (part == 1) { part1(actualInput) } else { part2(actualInput) }

            if (test) {
                println("Expected: $expectedOutput. Actual: $output")
            } else {
                println("Result: $output")
            }
        }

        runPart(1)
        println()
        runPart(2)
    }

    /** Part 1. */
    public abstract fun part1(input: List<String>): Any?

    /** Part 2. */
    public abstract fun part2(input: List<String>): Any?
}
