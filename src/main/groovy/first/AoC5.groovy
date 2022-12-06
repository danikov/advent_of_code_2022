package first

import static java.lang.Character.getNumericValue
import static java.lang.Character.isUpperCase

class AoC5 implements Runnable {
    void run() {
        def input = getClass().getResource("day3_input.txt").readLines()

        def rucksacks = input

        def commonLetters = rucksacks.collect{ sack ->
            def half = sack.length() / 2 as Integer
            def compartment1 = sack.take(half)
            def compartment2 = sack.takeRight(half)

//            println "$compartment1 : $compartment2"
            def common = (compartment1.chars as Set).intersect(compartment2.chars as Set) as List<Character>
//            println common
            return common.first()
        }

        println commonLetters
        println commonLetters.collect{Character.getNumericValue(it) - 9 + (Character.isUpperCase(it) ? 26 : 0)}.sum()
    }
}
