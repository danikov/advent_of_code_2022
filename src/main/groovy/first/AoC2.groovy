package first

class AoC2 implements Runnable {
    void run() {
        def input = getClass().getResource("day1_input.txt").text

        println (input.split("\n\n").collect {it.readLines()*.toInteger().sum()}.max())
        def elves = input.split("\n\n") as List
        def maxCalories = elves.collect {
            it.readLines()*.toInteger().sum()
        }.sort().takeRight(3).sum()

        println maxCalories
    }
}
