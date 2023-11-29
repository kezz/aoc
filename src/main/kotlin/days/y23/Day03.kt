package days.y23

import util.Day
import util.mapInner

public fun main() {
    Day3().run()
}

public class Day3 : Day(23, 3) {
    override fun part1(input: List<String>): Any = input
        .asSequence()
        .map(String::toCharArray)
        .map { items -> items.slice(0 until items.size / 2) to items.slice(items.size / 2 until items.size) }
        .map { (firstCompartment, secondCompartment) -> firstCompartment.intersect(secondCompartment.toSet()) }
        .map(Set<Char>::first)
        .map(Char::code)
        .sumOf { code -> if (code <= 90) { code - 38 } else { code - 96 } }

    override fun part2(input: List<String>): Any = input
        .asSequence()
        .chunked(3)
        .mapInner(String::toCharArray)
        .map { (first, second, third) -> first.intersect(second.toSet()).intersect(third.toSet()) }
        .map(Set<Char>::first)
        .map(Char::code)
        .sumOf { code -> if (code <= 90) { code - 38 } else { code - 96 } }
}
