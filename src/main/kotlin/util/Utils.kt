package util

/** Utility method to split the standard input format by empty lines for challenges. */
public fun List<String>.splitAroundBlankStrings(): Sequence<List<String>> = sequence {
    val tempList = mutableListOf<String>()

    this@splitAroundBlankStrings.forEach { value ->
        if (value.isBlank()) {
            yield(tempList.toList())
            tempList.clear()
        } else {
            tempList.add(value)
        }
    }

    if (tempList.isNotEmpty()) {
        yield(tempList)
    }
}

/** Maps the inner contents of a sequence of lists. */
public fun <I, O> Sequence<List<I>>.mapInner(transform: (I) -> O): Sequence<List<O>> =
    map { input -> input.map(transform) }

/** Maps the inner contents of a sequence of lists. */
public fun <I, O> Iterable<Iterable<I>>.mapInner(transform: (I) -> O): List<List<O>> =
    map { input -> input.map(transform) }

/** Prints and returns the receiver. */
public fun <T> T.debug(): T =
    also(::println)
