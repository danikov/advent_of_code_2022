package first

class AoC3 implements Runnable {
    void run() {
        def input = getClass().getResource("day2_input.txt").text

        def scores = [
            'A X' : 3 + 1,
            'A Y' : 6 + 2,
            'A Z' : 0 + 3,
            'B X' : 0 + 1,
            'B Y' : 3 + 2,
            'B Z' : 6 + 3,
            'C X' : 6 + 1,
            'C Y' : 0 + 2,
            'C Z' : 3 + 3,
        ]

        println input.readLines().collect{scores.get(it)}.sum()
    }
}
