package minimal

class AoC2 implements Runnable {
    void run() {
        println getClass().getResource("day1_input.txt").text
            .split("\n\n")
            .collect {it.readLines()*.toInteger().sum()}
            .sort()
            .takeRight(3)
            .sum()
    }
}
