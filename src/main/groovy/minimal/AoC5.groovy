package minimal

import static java.lang.Character.getNumericValue
import static java.lang.Character.isUpperCase

class AoC5 implements Runnable {
    void run() {
        println getClass().getResource("day3_input.txt").readLines()
            .collect { line ->
                def mid = line.length().intdiv(2)
                def common = (line.take(mid).chars as Set).intersect(line.takeRight(mid).chars as Set).first()
                getNumericValue(common) - 9 + (isUpperCase(common) ? 26 : 0)
            }
            .sum()
    }
}
