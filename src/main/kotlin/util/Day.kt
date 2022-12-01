package util

import kotlin.io.path.Path
import kotlin.io.path.readLines

/** A day. */
public abstract class Day(private val day: Int) {

    /** Runs the day. */
    public fun run() {
        fun runPart(part: Int) {
            require(part in 1..2)

            println("Running Day $day, Part $part...")
            val input = Path("src", "main", "resources", "day", "$day", "input.txt").readLines()
            val output = if (part == 1) { part1(input) } else { part2(input) }
            println("Result: $output")
        }

        runPart(1)
        println()
        runPart(2)
    }

    /** Part 1. */
    public abstract fun part1(input: List<String>): Any

    /** Part 2. */
    public abstract fun part2(input: List<String>): Any
}
