package minimal

class AoC8 implements Runnable {
    void run() {
        println getClass().getResource("day4_input.txt").readLines()
            .findAll { pair ->
                def ranges = pair.split(',').collect {
                    def parts = it.split('-')
                    new IntRange(parts[0] as Integer, parts[1] as Integer)
                }
                ranges[0].intersect(ranges[1]).size()
            }
            .size()
    }
}
