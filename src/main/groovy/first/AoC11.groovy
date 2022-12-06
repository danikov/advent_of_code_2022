package first

import com.google.common.collect.HashMultiset

class AoC11 implements Runnable {
    void run() {
        def input = getClass().getResource("day6_input.txt").text

        def window = new HashMultiset<Character>()
        int i = 4, nonuniques = 0
        (0..3).each {
            nonuniques += window.add(input[it], 1) == 1 ? 1 : 0
        }

        while (nonuniques > 0) {
            nonuniques -= window.remove(input[i-4], 1) == 2 ? 1 : 0
            nonuniques += window.add(input[i], 1) == 1 ? 1 : 0
            i++
        }

        println i
    }
}

