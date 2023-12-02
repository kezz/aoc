package days.y23

import util.Day

public fun main() {
    Day02().run()
}

public class Day02 : Day(23, 2) {
    private val limits = mapOf(
        "red" to 12,
        "green" to 13,
        "blue" to 14,
    )

    private fun parseInput(input: List<String>) = input
        .map { string -> string.split(": ") }
        .map { (gamePart, pulls) -> Pair(gamePart.split(' ')[1].toInt(), pulls.split("; ")) }
        .map { (gameIndex, pulls) ->
            gameIndex to pulls.foldIndexed(mutableMapOf<String, Int>()) { _, acc, string ->
                string
                    .split(", ")
                    .map { pull -> pull.split(' ')  }
                    .map { (number, colour) -> Pair(number.toInt(), colour) }
                    .forEach { (number, colour) ->
                        acc.compute(colour) { _, existing ->
                            when {
                                existing == null -> number
                                existing > number -> existing
                                else -> number
                            }
                        }
                    }
                acc
            }
        }

    override fun part1(input: List<String>): Any? = input
        .let(::parseInput)
        .filter { (_, maxSeen) ->
            maxSeen.all { (colour, number) -> number <= limits.getOrDefault(colour, 0) }
        }
        .sumOf(Pair<Int, MutableMap<String, Int>>::first)

    override fun part2(input: List<String>): Any? = input
        .let(::parseInput)
        .map(Pair<Int, MutableMap<String, Int>>::second)
        .map(MutableMap<String, Int>::values)
        .sumOf { maxes -> maxes.reduce(Int::times) }
}
