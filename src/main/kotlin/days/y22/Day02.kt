package days.y22

import util.Day

public fun main() {
    Day2().run()
}

public class Day2 : Day(22, 2) {
    override fun part1(input: List<String>): Any = input
        .map { string -> string.filterNot(Char::isWhitespace).toCharArray() }
        .map { (opponentCode, playerCode) -> Pair(opponentCode.code - 64, playerCode.code - 87) }
        .sumOf { (opponentsMove, playersMove) ->
            playersMove + when {
                opponentsMove == playersMove -> 3
                playersMove == 1 && opponentsMove == 3 -> 6
                opponentsMove == 1 && playersMove == 3 -> 0
                playersMove > opponentsMove -> 6
                else -> 0
            }
        }

    override fun part2(input: List<String>): Any = input
        .map { string -> string.filterNot(Char::isWhitespace).toCharArray() }
        .map { (opponentCode, targetCode) -> Pair(opponentCode.code - 64, (targetCode.code - 88) * 3) }
        .map { (opponentsMove, targetScore) ->
            targetScore to when (targetScore) {
                6 -> opponentsMove + 1
                3 -> opponentsMove
                else -> opponentsMove - 1
            }
        }
        .sumOf { (targetScore, uncorrectedPlayerMove) ->
            targetScore + when (uncorrectedPlayerMove) {
                4 -> 1
                0 -> 3
                else -> uncorrectedPlayerMove
            }
        }
}
