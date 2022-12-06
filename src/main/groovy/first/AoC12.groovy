package first

import com.google.common.collect.HashMultiset

class AoC12 implements Runnable {
    void run() {
        def input = getClass().getResource("day6_input.txt").text

        def window = new HashMultiset<Character>()
        int i = 14, nonuniques = 0
        (0..13).each {
            nonuniques += window.add(input[it], 1) == 1 ? 1 : 0
        }

        while (nonuniques > 0) {
            nonuniques -= window.remove(input[i-14], 1) == 2 ? 1 : 0
            nonuniques += window.add(input[i], 1) == 1 ? 1 : 0
            i++
        }

        println i
    }
}

