package first

class AoC1 implements Runnable {
    void run() {
        def input = getClass().getResource("day1_input.txt").text

        def elves = input.split("\n\n") as List
        def maxCalories = elves.collect {
            it.readLines()*.toInteger().sum()
        }.max()

        println maxCalories
    }
}
