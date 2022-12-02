package days

import util.*

public fun main() {
    Day1.run()
}

public object Day1 : Day(1) {
    override fun part1(input: List<String>): Any = input
        .splitAroundBlankStrings()
        .mapInner(String::toInt)
        .map(List<Int>::sum)
        .max()

    override fun part2(input: List<String>): Any = input
        .splitAroundBlankStrings()
        .mapInner(String::toInt)
        .map(List<Int>::sum)
        .sortedDescending()
        .take(3)
        .sum()
}
