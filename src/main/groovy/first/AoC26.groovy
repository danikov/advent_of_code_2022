package first

import com.google.common.collect.ImmutableList

class AoC26 implements Runnable {
    def input = getClass().getResource("/day13_input.txt").readLines()

    def divider1 = [[2]]
    def divider2 = [[6]]

    void run() {
        def packets = input.findResults { line ->
            if (line == "") return null
            return Eval.me(line)
        }
        packets << divider1
        packets << divider2

        packets.sort { c1, c2 ->
            compare(c1, c2)
        }

        println packets
        println "" + (packets.findIndexOf {it === divider1} + 1) * (packets.findIndexOf {it === divider2} + 1)

    }

    int compare(List left, List right) {
        for (int i = 0; i < Math.min(left.size(), right.size()); i++) {
            int comparison = compare(left[i], right[i])
            if (comparison) {
                return comparison
            }
        }

        left.size() <=> right.size()
    }

    int compare(Integer left, List right) {
        compare(ImmutableList.of(left), right)
    }

    int compare(List left, Integer right) {
        compare(left, ImmutableList.of(right))
    }

    int compare(Integer left, Integer right) {
        left <=> right
    }

}

