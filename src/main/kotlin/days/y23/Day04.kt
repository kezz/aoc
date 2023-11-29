package days.y23

import util.Day
import util.debug
import util.mapInner

public fun main() {
    Day4().run()
}

public class Day4 : Day(23, 4) {
    override fun part1(input: List<String>): Any = input
        .map { line -> line.split(',') }
        .mapInner { assignment -> assignment.split('-') }
        .mapInner { (firstSection, secondSection) -> firstSection.toInt()..secondSection.toInt() }.debug()
        .count { (firstPair, secondPair) ->
            (firstPair.first in secondPair && firstPair.last in secondPair) || (secondPair.first in firstPair && secondPair.last in firstPair)
        }

    override fun part2(input: List<String>): Any = input
        .map { line -> line.split(',') }
        .mapInner { assignment -> assignment.split('-') }
        .mapInner { (firstSection, secondSection) -> firstSection.toInt()..secondSection.toInt() }.debug()
        .count { (firstPair, secondPair) ->
            firstPair.first in secondPair || firstPair.last in secondPair || secondPair.first in firstPair || secondPair.last in firstPair
        }
}
