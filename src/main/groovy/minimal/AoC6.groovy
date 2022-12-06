package minimal

import static java.lang.Character.getNumericValue
import static java.lang.Character.isUpperCase

class AoC6 implements Runnable {
    void run() {
        println getClass().getResource("day3_input.txt").readLines()
            .collate(3)
            .collect { group ->
                def common = (group[0].chars as Set)
                    .intersect(group[1].chars as Set)
                    .intersect(group[2].chars as Set)
                    .first()
                getNumericValue(common) - 9 + (isUpperCase(common) ? 26 : 0)
            }
            .sum()
    }
}
