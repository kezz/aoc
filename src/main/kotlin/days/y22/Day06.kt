package days.y22

import arrow.core.Either
import arrow.core.recover
import util.Day

public fun main() {
    Day6().run()
}

public class Day6 : Day(22, 6) {
    override fun part1(input: List<String>): Any? = input
        .first()
        .foldIndexed<Either<List<Char>, Int>>(Either.Left(listOf())) { index, acc, char ->
            acc.recover { buffer ->
                when {
                    buffer.size < 4 -> raise(buffer.plus(char))
                    buffer.distinct().size == 4 -> index
                    else -> raise(buffer.drop(1).plus(char))
                }
            }
        }
        .getOrNull()

    override fun part2(input: List<String>): Any? = input
        .first()
        .foldIndexed<Either<List<Char>, Int>>(Either.Left(listOf())) { index, acc, char ->
            acc.recover { buffer ->
                when {
                    buffer.size < 14 -> raise(buffer.plus(char))
                    buffer.distinct().size == 14 -> index
                    else -> raise(buffer.drop(1).plus(char))
                }
            }
        }
        .getOrNull()
}
