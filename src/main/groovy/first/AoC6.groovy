package first

import static java.lang.Character.getNumericValue
import static java.lang.Character.isUpperCase

class AoC6 implements Runnable {
    void run() {
        def input = getClass().getResource("day3_input.txt").readLines()

        def rucksacks = input

        def groups = rucksacks.collate(3)
        def commonLetters = groups.collect { group ->
            def elf1 = group[0]
            def elf2 = group[1]
            def elf3 = group[2]

            return (elf1.chars as Set).intersect(elf2.chars as Set).intersect(elf3.chars as Set).first()
        }

        println commonLetters
        println commonLetters.collect{Character.getNumericValue(it) - 9 + (Character.isUpperCase(it) ? 26 : 0)}.sum()
    }
}
