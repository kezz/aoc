package days.y23

import util.Day
import util.mapInner
import util.splitAroundBlankStrings

public fun main() {
    Day5().run()
}

public class Day5 : Day(23, 5) {
    override fun part1(input: List<String>): Any = input
        .splitAroundBlankStrings()
        .toList()
        .let { (startState, instructions) -> startState to instructions.map { line -> line.split(' ').mapNotNull(String::toIntOrNull) } }
        .let { (startState, instructions) ->
            Triple(
                startState.slice(0 until startState.lastIndex).map(String::toCharArray),
                startState.last().toList().mapNotNull(Char::digitToIntOrNull).max(),
                instructions
            )
        }
        .let { (startState, maxCount, instructions) ->
            Pair(
                Array(maxCount) { index -> startState.mapNotNullTo(mutableListOf()) { chars -> chars.getOrNull((index * 4) + 1)?.takeIf(Char::isLetter) } }.toList(),
                instructions.mapInner { oneIndexedElement -> oneIndexedElement - 1 }
            )
        }
        .let { (state, instructions) ->
            state.apply { instructions.forEach { (amount, from, to) -> repeat(amount + 1) { state[to].add(0, state[from].removeFirst()) } } }
        }
        .map(MutableList<Char>::first)
        .joinToString(separator = "")

    override fun part2(input: List<String>): Any = input
        .splitAroundBlankStrings()
        .toList()
        .let { (startState, instructions) -> startState to instructions.map { line -> line.split(' ').mapNotNull(String::toIntOrNull) } }
        .let { (startState, instructions) ->
            Triple(
                startState.slice(0 until startState.lastIndex).map(String::toCharArray),
                startState.last().toList().mapNotNull(Char::digitToIntOrNull).max(),
                instructions
            )
        }
        .let { (startState, maxCount, instructions) ->
            Pair(
                Array(maxCount) { index -> startState.mapNotNullTo(mutableListOf()) { chars -> chars.getOrNull((index * 4) + 1)?.takeIf(Char::isLetter) } },
                instructions.mapInner { oneIndexedElement -> oneIndexedElement - 1 }
            )
        }
        .let { (state, instructions) ->
            state.apply {
                instructions.forEach { (amount, from, to) ->
                    state[to].addAll(0, state[from].take(amount + 1).also { state[from] = state[from].drop(amount + 1).toMutableList() })
                }
            }
        }
        .map(MutableList<Char>::first)
        .joinToString(separator = "")
}
