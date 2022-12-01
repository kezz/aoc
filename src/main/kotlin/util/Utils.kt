package util

/** Utility method to split the standard input format by empty lines for challenges. */
public fun List<String>.splitAroundBlankStrings(): Sequence<List<String>> = sequence {
    val tempList = mutableListOf<String>()

    this@splitAroundBlankStrings.forEach { value ->
        if (value.isBlank()) {
            yield(tempList)
            tempList.clear()
        } else {
            tempList.add(value)
        }
    }
}

/** Maps the inner contents of a sequence of lists. */
public fun <I, O> Sequence<List<I>>.mapInner(transform: (I) -> O): Sequence<List<O>> =
    map { input -> input.map(transform) }