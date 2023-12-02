package days.y23

import util.Day
import util.debug
import util.mapInner

public fun main() {
    Day01().run()
}

public class Day01 : Day(23, 1) {
    private val replacements = mapOf(
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9,
    )
    private val matchRegex = replacements
        .entries
        .map { (word, digit) -> listOf(word, digit.toString()) }
        .flatten()
        .joinToString(separator = "|")
        .toRegex()

    override fun part1(input: List<String>): Any = input
        .map(String::toCharArray)
        .mapInner(Char::digitToIntOrNull)
        .map(List<Int?>::filterNotNull)
        .map { list -> Pair(list.first(), list.last()) }
        .sumOf { (tens, units) -> (tens * 10) + units }

    override fun part2(input: List<String>): Any = input
        .map { string -> string.indices.mapNotNull { index -> matchRegex.find(string, index) } }
        .mapInner(MatchResult::value)
        .mapInner { value -> (value.toIntOrNull() ?: replacements[value])?.toString()?.debug() ?: value }
        .map(List<String>::joinToString)
        .let(::part1)
}
