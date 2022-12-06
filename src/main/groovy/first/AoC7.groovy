package first

class AoC7 implements Runnable {
    void run() {
        def input = getClass().getResource("day4_input.txt").readLines()

        def pairs = input

        def contains = pairs.findAll { pair ->
            def ranges = pair.split(',')
            def range1Vals = ranges[0].split('-')
            def range2Vals = ranges[1].split('-')
            def range1 = new IntRange(range1Vals[0] as Integer, range1Vals[1] as Integer)
            def range2 = new IntRange(range2Vals[0] as Integer, range2Vals[1] as Integer)

            return range1.containsAll(range2) || range2.containsAll(range1)
        }

        println contains.size()
    }
}
