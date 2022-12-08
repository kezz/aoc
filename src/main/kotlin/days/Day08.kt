package days

import util.Day

public fun main() {
    Day08().run()
}

public class Day08 : Day(8) {
    override fun part1(input: List<String>): Any = input
        .mapIndexed { y, row ->
            row.mapIndexed { x, tree ->
                tree.digitToInt().let { value ->
                    listOf(
                        (y - 1 downTo -1).all { checkY -> (input.getOrNull(checkY)?.getOrNull(x)?.digitToInt() ?: -1) < value },
                        (y + 1..input.size).all { checkY -> (input.getOrNull(checkY)?.getOrNull(x)?.digitToInt() ?: -1) < value },
                        (x - 1 downTo -1).all { checkX -> (input.getOrNull(y)?.getOrNull(checkX)?.digitToInt() ?: -1) < value },
                        (x + 1..row.length).all { checkX -> (input.getOrNull(y)?.getOrNull(checkX)?.digitToInt() ?: -1) < value },
                    ).any { it }
                }
            }
        }
        .flatten()
        .count { it }

    override fun part2(input: List<String>): Any = input
        .drop(1)
        .dropLast(1)
        .map { row -> row.drop(1).dropLast(1) }
        .let { parsedInput ->
            parsedInput.mapIndexed { y, row ->
                row.mapIndexed { x, tree ->
                    tree.digitToInt().let { value ->
                        listOf(
                            (y - 1 downTo -1).fold(Pair(0, 0)) { (lastSeen, count), checkY ->
                                if (lastSeen >= value) {
                                    lastSeen to count
                                } else {
                                    Pair(parsedInput.getOrNull(checkY)?.getOrNull(x)?.digitToInt() ?: -1, count + 1)
                                }
                            }.second,
                            (y + 1..parsedInput.size).fold(Pair(0, 0)) { (lastSeen, count), checkY ->
                                if (lastSeen >= value) {
                                    lastSeen to count
                                } else {
                                    Pair(parsedInput.getOrNull(checkY)?.getOrNull(x)?.digitToInt() ?: -1, count + 1)
                                }
                            }.second,
                            (x - 1 downTo -1).fold(Pair(0, 0)) { (lastSeen, count), checkX ->
                                if (lastSeen >= value) {
                                    lastSeen to count
                                } else {
                                    Pair(parsedInput.getOrNull(y)?.getOrNull(checkX)?.digitToInt() ?: -1, count + 1)
                                }
                            }.second,
                            (x + 1..row.length).fold(Pair(0, 0)) { (lastSeen, count), checkX ->
                                if (lastSeen >= value) {
                                    lastSeen to count
                                } else {
                                    Pair(parsedInput.getOrNull(y)?.getOrNull(checkX)?.digitToInt() ?: -1, count + 1)
                                }
                            }.second,
                        ).reduce(Int::times)
                    }
                }
            }
        }
        .flatten()
        .max()
}
