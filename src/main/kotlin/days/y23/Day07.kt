package days.y23

import arrow.core.Either
import arrow.core.Either.Left
import arrow.core.Either.Right
import util.Day

public fun main() {
    Day07().run()
}

public class Day07 : Day(23, 7) {
    override fun part1(input: List<String>): Any = input
        .mapNotNull { line ->
            when {
                line.startsWith("$ cd") -> Left(line.substringAfterLast(' '))
                line.startsWith("$ ls") -> null
                else -> line.substringBefore(' ').toIntOrNull()?.let(::Right)
            }
        }
        .fold<Either<String, Int>, Pair<List<String>, Map<String, Int>>>(Pair(listOf("~"), mapOf())) { acc, element ->
            element.fold(
                ifLeft = { movement ->
                    when (movement) {
                        ".." -> acc.copy(first = acc.first.dropLast(1))
                        "/" -> acc.copy(first = listOf("~"))
                        else -> acc.first.plus(movement).let { tree -> acc.copy(tree, acc.second.toMutableMap().apply { merge(tree.joinToString("/"), 0, Int::plus) }) }
                    }
                },
                ifRight = { fileSize ->
                    acc.copy(second = acc.second.toMutableMap().apply { merge(acc.first.joinToString("/"), fileSize, Int::plus) })
                }
            )
        }
        .second
        .let { map ->
            map.entries.mapNotNull { (path, fileContentsSize) ->
                map.filter { (key) -> key != path && key.startsWith(path) && key.removePrefix(path).first() == '/' }
                    .values
                    .sum()
                    .plus(fileContentsSize)
                    .takeIf { size -> size <= 100_000 }
            }
        }
        .sum()

    override fun part2(input: List<String>): Any = input
        .mapNotNull { line ->
            when {
                line.startsWith("$ cd") -> Left(line.substringAfterLast(' '))
                line.startsWith("$ ls") -> null
                else -> line.substringBefore(' ').toIntOrNull()?.let(::Right)
            }
        }
        .fold<Either<String, Int>, Triple<List<String>, Map<String, Int>, Int>>(Triple(listOf("~"), mapOf(), 0)) { acc, element ->
            element.fold(
                ifLeft = { movement ->
                    when (movement) {
                        ".." -> acc.copy(first = acc.first.dropLast(1))
                        "/" -> acc.copy(first = listOf("~"))
                        else -> acc.first.plus(movement).let { tree -> acc.copy(tree, acc.second.toMutableMap().apply { merge(tree.joinToString("/"), 0, Int::plus) }) }
                    }
                },
                ifRight = { fileSize ->
                    acc.copy(
                        second = acc.second.toMutableMap().apply { merge(acc.first.joinToString("/"), fileSize, Int::plus) },
                        third = acc.third + fileSize
                    )
                }
            )
        }
        .let { triple -> triple.copy(third = 30_000_000 - (70_000_000 - triple.third)) }
        .let { (_, map, target) ->
            target to map.mapValues { (path, fileContentsSize) ->
                map.filter { (key) -> key != path && key.startsWith(path) && key.removePrefix(path).first() == '/' }
                    .values
                    .sum()
                    .plus(fileContentsSize)
            }
        }
        .let { (target, map) ->
            map.entries
                .sortedBy(Map.Entry<String, Int>::value)
                .first { (_, size) -> size > target }
        }
        .value
}
