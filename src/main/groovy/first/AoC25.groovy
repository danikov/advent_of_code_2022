package first

import com.google.common.collect.ImmutableList

class AoC25 implements Runnable {
    def input = getClass().getResource("/day13_input.txt").text

    def rightOrder = []

    void run() {
        def pairs = input.split('\n\n').collect { pair ->
            def parts = pair.split('\n')
            return new Tuple2(Eval.me(parts[0]), Eval.me(parts[1]))
        }

        pairs.eachWithIndex { Tuple2 pair, int i ->
            compare(pair.v1, pair.v2, i + 1)
        }

        println rightOrder
        println rightOrder.sum()
    }

    Boolean compare(List left, List right, Integer index) {
        for (int i = 0; i < Math.min(left.size(), right.size()); i++) {
            if (compare(left[i], right[i], index)) {
                return true
            }
        }

        if (left.size() < right.size()) {
            rightOrder << index
            return true
        } else if (left.size() > right.size()) {
            return true
        }
        return false
    }

    Boolean compare(Integer left, List right, Integer index) {
        compare(ImmutableList.of(left), right, index)
    }

    Boolean compare(List left, Integer right, Integer index) {
        compare(left, ImmutableList.of(right), index)
    }

    Boolean compare(Integer left, Integer right, Integer index) {
        if (left < right) {
            rightOrder << index
            return true
        } else if (left > right) {
            return true
        }
        return false
    }

}

