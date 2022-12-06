package minimal

class AoC1 implements Runnable {
    void run() {
        println getClass().getResource("day1_input.txt").text
            .split("\n\n")
            .collect {it.readLines()*.toInteger().sum()}
            .max()
    }
}
