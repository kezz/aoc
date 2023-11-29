package days.y21

import util.Day

public fun main() {
    Day1.run()
}

public object Day1 : Day(21, 1) {
    override fun part1(input: List<String>): Any = input
        .map(String::toInt)
        .fold(Pair<Int, Int?>(0, null)) { (acc, prev), cur ->
            when {
                prev == null -> Pair(0, cur)
                prev < cur -> Pair(acc.inc(), cur)
                else -> Pair(acc, cur)
            }
        }
        .first

    override fun part2(input: List<String>): Any = input
        .map(String::toInt)
        .windowed(3, 1)
        .map(List<Int>::sum)
        .fold(Pair<Int, Int?>(0, null)) { (acc, prev), cur ->
            when {
                prev == null -> Pair(0, cur)
                prev < cur -> Pair(acc.inc(), cur)
                else -> Pair(acc, cur)
            }
        }
        .first
}
